package com.cross.chain.payment.service.payment;

import com.cross.chain.payment.dto.PaymentRequest;
import com.cross.chain.payment.dto.PaymentResponse;

public interface PaymentService {

    PaymentResponse processPaymentRequest(PaymentRequest paymentRequest);

    PaymentRequest retrievePaymentRequest(String paymentHash);

}
