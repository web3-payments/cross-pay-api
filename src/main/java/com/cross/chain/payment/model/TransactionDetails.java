package com.cross.chain.payment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class TransactionDetails {

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
