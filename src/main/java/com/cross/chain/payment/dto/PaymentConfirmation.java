package com.cross.chain.payment.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * PaymentConfirmation
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class PaymentConfirmation {

    @JsonProperty("transactionHash")
    private String transactionHash;

    @JsonProperty("blockHash")
    private String blockHash;

    @JsonProperty("blockNumber")
    private int blockNumber;

    @JsonProperty("gasUsed")
    private String gasUsed;

    @JsonProperty("toAddress")
    private String toAddress;

    @JsonProperty("fromAddress")
    private String fromAddress;

    @JsonProperty("confirmations")
    private int confirmations;

}
