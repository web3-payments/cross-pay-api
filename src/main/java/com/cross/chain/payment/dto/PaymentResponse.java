package com.cross.chain.payment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {

    private String paymentLink;

}
