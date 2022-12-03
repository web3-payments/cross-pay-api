package com.cross.chain.payment.service.payment;

import com.cross.chain.payment.domain.PaymentRequestDetails;
import com.cross.chain.payment.dto.PaymentConfirmationDTO;
import com.cross.chain.payment.dto.PaymentRequest;
import com.cross.chain.payment.dto.PaymentResponse;
import com.cross.chain.payment.domain.PaymentType;

public interface PaymentService {

    PaymentResponse create(PaymentRequest paymentRequest);

    PaymentRequestDetails confirm(PaymentRequestDetails paymentRequest, PaymentConfirmationDTO paymentConfirmationDTO);

    PaymentRequestDetails cancel(PaymentRequestDetails paymentRequest);

    void validatePayment(PaymentRequest paymentRequest);
    default boolean applies(PaymentType type){
        return typePayment().equals(type);
    }
    PaymentType typePayment();

}
