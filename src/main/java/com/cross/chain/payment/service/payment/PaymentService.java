package com.cross.chain.payment.service.payment;

import com.cross.chain.payment.dto.PaymentConfirmation;
import com.cross.chain.payment.dto.PaymentRequest;
import com.cross.chain.payment.dto.PaymentResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PaymentService {

    PaymentResponse processPaymentRequest(PaymentRequest paymentRequest);

    PaymentRequest retrievePaymentRequest(String paymentHash);

    List<PaymentRequest> retrieveByUserAddress(String address);

    void paymentConfirmation(String paymentHash, PaymentConfirmation paymentConfirmation);

    void paymentCancellation(String paymentHash);
}
