package com.cross.chain.payment.service.payment;

import com.cross.chain.payment.exception.PaymentRequestNotFound;
import com.cross.chain.payment.model.*;

import java.util.List;

public interface PaymentProcessorService {

    PaymentResponse processPaymentRequest(PaymentRequest paymentRequest);

    PaymentRequest retrievePaymentRequest(String paymentHash) throws PaymentRequestNotFound;

    List<Transaction> retrievePaymentTransactions(String paymentHash) throws PaymentRequestNotFound;

    PaymentRequest updatePaymentRequest(String paymentHash, PaymentRequest paymentRequest) throws PaymentRequestNotFound;

    List<PaymentRequest> retrieveByUserAddress(String address);

    void paymentConfirmation(String paymentHash, PaymentConfirmationRequest paymentConfirmation) throws PaymentRequestNotFound;

    void paymentCancellation(String paymentHash) throws PaymentRequestNotFound;
}
