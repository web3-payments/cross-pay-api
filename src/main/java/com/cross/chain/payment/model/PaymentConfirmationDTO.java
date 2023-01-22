package com.cross.chain.payment.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * PaymentConfirmation
 */
@Data
@Builder
public class PaymentConfirmationDTO {
    private TransactionDetailsDTO transactionDetails;
    private BigDecimal amount;
    private List<ProductPaymentRequest> products;
    private CustomerInfoDTO customerInfoDTO;
}