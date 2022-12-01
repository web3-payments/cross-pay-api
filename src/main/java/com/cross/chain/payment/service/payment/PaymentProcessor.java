package com.cross.chain.payment.service.payment;

import com.cross.chain.payment.dto.PaymentConfirmation;
import com.cross.chain.payment.dto.PaymentRequest;
import com.cross.chain.payment.dto.PaymentResponse;
import com.cross.chain.payment.exception.PaymentRequestNotFound;

import java.util.List;

public interface PaymentProcessor {

    PaymentResponse processPaymentRequest(PaymentRequest paymentRequest);

    PaymentRequest retrievePaymentRequest(String paymentHash) throws PaymentRequestNotFound;

    PaymentRequest updatePaymentRequest(String paymentHash, PaymentRequest paymentRequest) throws PaymentRequestNotFound;

    List<PaymentRequest> retrieveByUserAddress(String address);

    void paymentConfirmation(String paymentHash, PaymentConfirmation paymentConfirmation) throws PaymentRequestNotFound;

    void paymentCancellation(String paymentHash) throws PaymentRequestNotFound;
}
