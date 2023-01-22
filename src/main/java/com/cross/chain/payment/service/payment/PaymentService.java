package com.cross.chain.payment.service.payment;

import com.cross.chain.payment.model.PaymentConfirmationDTO;
import com.cross.chain.payment.model.PaymentRequest;
import com.cross.chain.payment.model.PaymentRequestDTO;
import com.cross.chain.payment.model.PaymentResponse;
import com.cross.chain.payment.model.enums.PaymentType;

public interface PaymentService {

    PaymentResponse create(PaymentRequest paymentRequest);

    PaymentRequestDTO confirm(PaymentRequestDTO paymentRequest, PaymentConfirmationDTO paymentConfirmationDTO);

    PaymentRequestDTO cancel(PaymentRequestDTO paymentRequest);

    void validatePayment(PaymentRequest paymentRequest);
    default boolean applies(PaymentType type){
        return typePayment().equals(type);
    }
    PaymentType typePayment();

}
