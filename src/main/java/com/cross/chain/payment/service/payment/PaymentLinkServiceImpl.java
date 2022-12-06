package com.cross.chain.payment.service.payment;

import com.cross.chain.payment.domain.*;
import com.cross.chain.payment.dto.*;
import com.cross.chain.payment.exception.ProductNotFoundException;
import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.mapper.PaymentRequestMapper;
import com.cross.chain.payment.mapper.ProductMapper;
import com.cross.chain.payment.mapper.TransactionMapper;
import com.cross.chain.payment.repository.PaymentRequestRepository;
import com.cross.chain.payment.repository.TransactionRepository;
import com.cross.chain.payment.repository.UserRepository;
import com.cross.chain.payment.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentLinkServiceImpl implements PaymentService {

    @Value("${payment.hash-length}")
    private int hashLength;

    @Value("${payment.url}")
    private String url;

    private final PaymentRequestMapper paymentRequestMapper;

    private final ProductMapper productMapper;

    private final TransactionMapper transactionMapper;

    private final PaymentRequestRepository repository;

    private final UserRepository userRepository;

    private final TransactionRepository transactionRepository;
    private final ProductService productService;

    @Override
    public PaymentResponse create(PaymentRequest paymentRequest) {
        validatePayment(paymentRequest);
        PaymentRequestDetails paymentRequestDetails;
        try {
            paymentRequestDetails = createLink(paymentRequest);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e); //TODO: Create a paymentLinkCreation Exception
        }
        return PaymentResponse.builder()
                .paymentLink(paymentRequestDetails.getPaymentLink())
                .build();
    }

    @Override
    public PaymentRequestDetails confirm(PaymentRequestDetails paymentRequest, PaymentConfirmationDTO paymentConfirmationDTO) {
        Transaction transaction = transactionMapper.map(paymentConfirmationDTO);
        transaction.setCryptocurrency(paymentRequest.getCryptocurrency());
        updateProductDetails(transaction.getProducts());
        paymentRequest.getTransactions().add(transactionRepository.save(transaction));
        return repository.save(paymentRequest);
    }

    @Override
    public PaymentRequestDetails cancel(PaymentRequestDetails paymentRequest) {
        //TODO: create a transaction history using the paymentConfirmation
        if(paymentRequest.getPaymentStatus().isFinalStatus()){
            throw new RuntimeException(); //TODO: change exception
        }
        //TODO: increase the total supply since the payment was cancelled.
        paymentRequest.setPaymentStatus(PaymentStatus.DEACTIVATED);
        return repository.save(paymentRequest);
    }

    @Override
    public void validatePayment(PaymentRequest paymentRequest) {
        //TODO: validate if contains all the required information to create the link
        Assert.notNull(paymentRequest.getAmount(), String.format("Amount is required for Payment type: %s", PaymentType.PAYMENT_LINK.name()));
    }

    @Override
    public PaymentType typePayment() {
        return PaymentType.PAYMENT_LINK;
    }


    private PaymentRequestDetails createLink(PaymentRequest paymentRequest) throws UserNotFoundException {
        PaymentRequestDetails paymentRequestDetails = paymentRequestMapper.map(paymentRequest);
        //updateProductDetails(paymentRequestDetails.getProducts()); // product will be updated during the settlement
        User user = userRepository.findBySignerAddress(paymentRequest.getUserAddress()).orElseThrow(UserNotFoundException::new);
        paymentRequestDetails.setUser(user);
        paymentRequestDetails.setHash(RandomStringUtils.randomAlphabetic(hashLength));
        paymentRequestDetails.setPaymentLink(url.concat("/").concat(paymentRequestDetails.getHash()));
        paymentRequestDetails.setPaymentStatus(PaymentStatus.ACTIVATED);
        return repository.save(paymentRequestDetails);
    }

    private void updateProductDetails(List<ProductsPayment> products) {
        products.forEach(item-> {
            ProductResponse product;
            try {
                product = productService.retrieveById(item.getProduct().getId());
            } catch (ProductNotFoundException e) {
                throw new RuntimeException(e); //TODO: throw an payment Execution error - Product not found
            }
            if(item.getQuantity() > product.getTotalSupply()){
                throw new RuntimeException(); //TODO: throw an payment Execution error - Amount selected not valid
            }
            product.setTotalSupply(product.getTotalSupply() - item.getQuantity());
            productService.update(productMapper.map(product));
        });
    }
}
