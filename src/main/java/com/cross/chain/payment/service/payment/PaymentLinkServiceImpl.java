package com.cross.chain.payment.service.payment;

import com.cross.chain.payment.exception.PaymentLinkCreationException;
import com.cross.chain.payment.exception.ProductNotFoundException;
import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.mapper.PaymentRequestMapper;
import com.cross.chain.payment.mapper.ProductMapper;
import com.cross.chain.payment.mapper.TransactionMapper;
import com.cross.chain.payment.model.*;
import com.cross.chain.payment.model.enums.PaymentStatus;
import com.cross.chain.payment.model.enums.PaymentType;
import com.cross.chain.payment.persistence.*;
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

    private final PaymentRequestDbService paymentRequestDbService;

    private final UserDbService userDbService;

    private final TransactionDbService transactionDbService;
    private final ProductDbService productDbService;

    @Override
    public PaymentResponse create(PaymentRequest paymentRequest) {
        validatePayment(paymentRequest); //TODO: move this to a bean validator?
        PaymentRequestDTO paymentRequestDTO = paymentRequestMapper.map(paymentRequest);
        try {
            paymentRequestDTO = createLink(paymentRequestDTO);
        } catch (UserNotFoundException e) {
            throw new PaymentLinkCreationException(e);
        }
        return PaymentResponse.builder()
                .paymentLink(paymentRequestDTO.getPaymentLink())
                .build();
    }

    @Override
    public PaymentRequestDTO confirm(PaymentRequestDTO paymentRequest, PaymentConfirmationDTO paymentConfirmationDTO) {
        TransactionDTO transaction = transactionMapper.map(paymentConfirmationDTO);
        transaction.setCryptocurrency(paymentRequest.getCryptocurrency());
        updateProductDetails(transaction.getProducts());
        paymentRequest.getTransactions().add(transactionDbService.save(transaction));
        return paymentRequestDbService.save(paymentRequest);
    }

    @Override
    public PaymentRequestDTO cancel(PaymentRequestDTO paymentRequest) {
        //TODO: create a transaction history using the paymentConfirmation
        if(paymentRequest.getPaymentStatus().isFinalStatus()){
            throw new RuntimeException(); //TODO: change exception
        }
        //TODO: increase the total supply since the payment was cancelled.
        paymentRequest.setPaymentStatus(PaymentStatus.DEACTIVATED);
        return paymentRequestDbService.save(paymentRequest);
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


    private PaymentRequestDTO createLink(PaymentRequestDTO paymentRequestDTO) throws UserNotFoundException {
        UserDTO user = userDbService.findBySignerAddress(paymentRequestDTO.getUserAddress());
        paymentRequestDTO.setUser(user);
        paymentRequestDTO.setHash(RandomStringUtils.randomAlphabetic(hashLength));
        paymentRequestDTO.setPaymentLink(url.concat("/").concat(paymentRequestDTO.getHash()));
        paymentRequestDTO.setPaymentStatus(PaymentStatus.ACTIVATED);
        return paymentRequestDbService.save(paymentRequestDTO);
    }

    private void updateProductDetails(List<ProductPaymentDTO> products) {
        products.forEach(item-> {
            ProductDTO product;
            try {
                product = productDbService.findById(item.getProduct().getId());
            } catch (ProductNotFoundException e) {
                throw new RuntimeException(e); //TODO: throw an payment Execution error - Product not found
            }
            if(item.getQuantity() > product.getTotalSupply()){
                throw new RuntimeException(); //TODO: throw an payment Execution error - Amount selected not valid
            }
            product.setTotalSupply(product.getTotalSupply() - item.getQuantity());
            productDbService.save(product);
        });
    }
}
