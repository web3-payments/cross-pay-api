package com.cross.chain.payment.mapper;

import com.cross.chain.payment.domain.PaymentRequestDetails;
import com.cross.chain.payment.dto.PaymentRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentRequestMapper {

    PaymentRequestDetails map(PaymentRequest paymentRequest);

    PaymentRequest map(PaymentRequestDetails paymentRequestDetails);

}
