package com.cross.chain.payment.service.payment;

import com.cross.chain.payment.domain.*;
import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.mapper.PaymentRequestMapper;
import com.cross.chain.payment.dto.PaymentRequest;
import com.cross.chain.payment.dto.PaymentResponse;
import com.cross.chain.payment.repository.PaymentRequestRepository;
import com.cross.chain.payment.repository.UserRepository;
import com.cross.chain.payment.service.product.ProductService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class PaymentLinkServiceImpl implements PaymentService {

    @Value("${payment.hash-length}")
    private int hashLength;

    @Value("${payment.url}")
    private String url;

    @Autowired
    private PaymentRequestMapper mapper;

    @Autowired
    private PaymentRequestRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Override
    public PaymentResponse create(PaymentRequest paymentRequest) {
        validatePayment(paymentRequest);
        PaymentRequestDetails paymentRequestDetails = null;
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
    public PaymentRequestDetails confirm(PaymentRequestDetails paymentRequest) {
        //TODO: create a transaction history using the paymentConfirmation - PAYM-42
        //paymentRequestDetails.setPaymentStatus(PaymentStatus.PAID);
        //repository.save(paymentRequestDetails);
        return null;
    }

    @Override
    public PaymentRequestDetails cancel(PaymentRequestDetails paymentRequest) {
        //TODO: create a transaction history using the paymentConfirmation
        if(paymentRequest.getPaymentStatus().isFinalStatus()){
            throw new RuntimeException(); //TODO: change exception
        }
        //TODO: increate the total supply since the payment was cancelled.
        paymentRequest.setPaymentStatus(PaymentStatus.DEACTIVATED);
        return repository.save(paymentRequest);
    }

    private PaymentRequestDetails createLink(PaymentRequest paymentRequest) throws UserNotFoundException {
        PaymentRequestDetails paymentRequestDetails = mapper.map(paymentRequest);
        updateProductDetails(paymentRequestDetails.getProducts());
        User user = userRepository.findBySignerAddress(paymentRequest.getUserAddress()).orElseThrow(UserNotFoundException::new);
        paymentRequestDetails.setUser(user);
        paymentRequestDetails.setHash(RandomStringUtils.randomAlphabetic(hashLength));
        paymentRequestDetails.setPaymentLink(url.concat("/").concat(paymentRequestDetails.getHash()));
        paymentRequestDetails.setPaymentStatus(PaymentStatus.ACTIVATED);
        return repository.save(paymentRequestDetails);
    }

    private void updateProductDetails(List<ProductsPayment> products) {
        products.forEach(item-> productService.update(item.getProduct()));
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

}
