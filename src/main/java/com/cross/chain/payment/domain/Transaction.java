package com.cross.chain.payment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "transaction")
public class Transaction {

    @Id
    private String id;
    private String transactionHash;
    private String blockHash;
    private int blockNumber;
    private String gasUsed;
    private String toAddress;
    private String fromAddress;
    private int confirmations;
    private CustomerInfo customerInfo;

}