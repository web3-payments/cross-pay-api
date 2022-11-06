package com.cross.chain.payment.service.payment;

import com.cross.chain.payment.domain.PaymentRequestDetails;
import com.cross.chain.payment.domain.PaymentStatus;
import com.cross.chain.payment.dto.PaymentConfirmation;
import com.cross.chain.payment.mapper.PaymentRequestMapper;
import com.cross.chain.payment.dto.PaymentRequest;
import com.cross.chain.payment.dto.PaymentResponse;
import com.cross.chain.payment.repository.PaymentRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<PaymentRequest> retrieveByUserAddress(String address) {
        return repository.findByUserAddress(address)
                .stream().map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void paymentConfirmation(String paymentHash, PaymentConfirmation paymentConfirmation) {
        PaymentRequestDetails paymentRequestDetails = repository.findByHash(paymentHash).orElseThrow(RuntimeException::new); //TODO: change exception
        //TODO: create a transaction history using the paymentConfirmation
        paymentRequestDetails.setPaymentStatus(PaymentStatus.PAID);
        repository.save(paymentRequestDetails);
    }

    @Override
    public void paymentCancellation(String paymentHash) {
        PaymentRequestDetails paymentRequestDetails = repository.findByHash(paymentHash).orElseThrow(RuntimeException::new); //TODO: change exception
        //TODO: create a transaction history using the paymentConfirmation
        if(paymentRequestDetails.getPaymentStatus().isFinalStatus()){
            throw new RuntimeException();
        }
        paymentRequestDetails.setPaymentStatus(PaymentStatus.CANCELLED);
        repository.save(paymentRequestDetails);
    }
}
