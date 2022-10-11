package com.cross.chain.payment.service;

import com.cross.chain.payment.model.PaymentRequest;
import com.cross.chain.payment.model.PaymentResponse;
import com.cross.chain.payment.model.PaymentType;

public interface PaymentRequestService {

    PaymentResponse createPaymentRequest(PaymentRequest paymentRequest);

    void validatePayment(PaymentRequest paymentRequest);
    default boolean applies(PaymentType type){
        return typePayment().equals(type);
    }
    PaymentType typePayment();

}
