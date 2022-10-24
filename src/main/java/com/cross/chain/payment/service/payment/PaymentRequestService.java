package com.cross.chain.payment.service.payment;

import com.cross.chain.payment.dto.PaymentRequest;
import com.cross.chain.payment.dto.PaymentResponse;
import com.cross.chain.payment.dto.PaymentType;

public interface PaymentRequestService {

    PaymentResponse createPaymentRequest(PaymentRequest paymentRequest);

    void validatePayment(PaymentRequest paymentRequest);
    default boolean applies(PaymentType type){
        return typePayment().equals(type);
    }
    PaymentType typePayment();

}
