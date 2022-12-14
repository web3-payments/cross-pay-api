package com.cross.chain.payment.service.payment;

import com.cross.chain.payment.dto.*;
import com.cross.chain.payment.exception.PaymentRequestNotFound;

import java.util.List;

public interface PaymentProcessor {

    PaymentResponse processPaymentRequest(PaymentRequest paymentRequest);

    PaymentRequest retrievePaymentRequest(String paymentHash) throws PaymentRequestNotFound;

    List<TransactionDTO> retrievePaymentTransactions(String paymentHash) throws PaymentRequestNotFound;

    PaymentRequest updatePaymentRequest(String paymentHash, PaymentRequest paymentRequest) throws PaymentRequestNotFound;

    List<PaymentRequest> retrieveByUserAddress(String address);

    void paymentConfirmation(String paymentHash, PaymentConfirmationDTO paymentConfirmationDTO) throws PaymentRequestNotFound;

    void paymentCancellation(String paymentHash) throws PaymentRequestNotFound;
}
