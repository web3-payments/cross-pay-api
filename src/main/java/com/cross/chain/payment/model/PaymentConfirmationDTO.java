package com.cross.chain.payment.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * PaymentConfirmation
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentConfirmationDTO {
    private TransactionDetailsDTO transactionDetails;
    private BigDecimal amount;
    private List<ProductPaymentRequest> products;
    private CustomerInfoDTO customerInfoDTO;
}