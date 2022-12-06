package com.cross.chain.payment.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
    @Indexed
    @CreatedDate
    private Instant executionDate;
    private BigDecimal amount;
    @DBRef
    private Cryptocurrency cryptocurrency;
    private List<ProductsPayment> products = new ArrayList<>();
    private CustomerInfo customerInfo;

}