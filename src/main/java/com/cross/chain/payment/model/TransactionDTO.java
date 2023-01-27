package com.cross.chain.payment.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TransactionDTO {
    private String id;
    private String transactionHash;
    private String blockHash;
    private int blockNumber;
    private String gasUsed;
    private String toAddress;
    private String fromAddress;
    private int confirmations;
    private LocalDateTime executionDate;
    private BigDecimal amount;
    private CryptocurrencyDTO cryptocurrency;
    private CustomerInfoDTO customerInfo;
    private List<ProductPaymentDTO> products;
}