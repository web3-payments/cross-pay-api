package com.cross.chain.payment.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDetailsDTO {
    private String transactionHash;
    private String blockHash;
    private int blockNumber;
    private String gasUsed;
    private String toAddress;
    private String fromAddress;
    private int confirmations;
}