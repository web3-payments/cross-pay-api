package com.cross.chain.payment.service;

import com.cross.chain.payment.model.PaymentRequest;
import com.cross.chain.payment.model.PaymentResponse;

public interface PaymentService {

    PaymentResponse processPaymentRequest(PaymentRequest paymentRequest);

    PaymentRequest retrievePaymentRequest(String paymentHash);

}
