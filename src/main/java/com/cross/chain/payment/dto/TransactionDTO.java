package com.cross.chain.payment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class TransactionDTO {

    @JsonProperty("id")
    private String id;
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
    @JsonProperty("executionDate")
    private Instant executionDate;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("cryptocurrency")
    private CryptocurrencyDTO cryptocurrency;
    @JsonProperty("customerInfo")
    private CustomerInfoDTO customerInfoDTO;
    @Valid
    @JsonProperty("products")
    private List<ProductPaymentRequest> products;

}