package com.cross.chain.payment.persistence;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "transaction")
class TransactionEntity {

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
    private LocalDateTime executionDate;
    private BigDecimal amount;
    @DBRef
    private CryptocurrencyEntity cryptocurrency;
    private CustomerInfo customerInfo;
    private List<ProductsPayment> products = new ArrayList<>();

}