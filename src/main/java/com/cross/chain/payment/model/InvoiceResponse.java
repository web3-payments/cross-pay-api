package com.cross.chain.payment.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvoiceResponse {

    private String paymentLink;

}
