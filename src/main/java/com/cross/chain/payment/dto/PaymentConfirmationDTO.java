package com.cross.chain.payment.dto;


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
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class PaymentConfirmationDTO {

    @Valid
    @JsonProperty("transactionDetails")
    private TransactionDetailsDTO transactionDetailsDTO;

    @Valid
    @JsonProperty("amountPaid")
    private BigDecimal amount;

    @Valid
    @JsonProperty("products")
    private List<ProductPaymentRequest> products;

    @Valid
    @JsonProperty("customerInfo")
    private CustomerInfoDTO customerInfoDTO;

}
