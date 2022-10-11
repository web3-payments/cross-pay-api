package com.cross.chain.payment.service;

import com.cross.chain.payment.domain.PaymentRequestDetails;
import com.cross.chain.payment.mapper.PaymentRequestMapper;
import com.cross.chain.payment.model.PaymentRequest;
import com.cross.chain.payment.model.PaymentResponse;
import com.cross.chain.payment.repository.PaymentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    List<PaymentRequestService> paymentRequestServices;

    @Autowired
    private PaymentRequestMapper mapper;

    @Autowired
    private PaymentRequestRepository repository;

    @Override
    public PaymentResponse processPaymentRequest(PaymentRequest paymentRequest) {
        PaymentRequestService paymentRequestService = paymentRequestServices.stream()
                .filter(p -> p.applies(paymentRequest.getPaymentType()))
                .findFirst()
                .orElseThrow(RuntimeException::new);//TODO: change exception
        return paymentRequestService.createPaymentRequest(paymentRequest);
    }

    @Override
    public PaymentRequest retrievePaymentRequest(String paymentHash) {
        PaymentRequestDetails paymentRequestDetails = repository.findByHash(paymentHash).orElseThrow(RuntimeException::new);//TODO: change exception
        return mapper.map(paymentRequestDetails);
    }
}
