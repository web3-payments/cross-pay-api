package com.cross.chain.payment.persistence;

import com.cross.chain.payment.model.CustomerRequiredInfo;
import com.cross.chain.payment.model.enums.PaymentStatus;
import com.cross.chain.payment.model.enums.PaymentType;
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
@Document(collection = "payment-request")
class PaymentRequestEntity {

    @Id
    private String id;
    private String description;
    private String creditAddress;
    private String userAddress;
    private String hash;
    private String paymentLink;
    @Indexed
    @CreatedDate
    private Instant createdAt;
    private BigDecimal amount;
    @DBRef
    private UserEntity user;
    @DBRef
    private CryptocurrencyEntity cryptocurrency;
    private PaymentType paymentType;
    private PaymentStatus paymentStatus;
    private List<ProductsPayment> products = new ArrayList<>();
    @DBRef
    private List<TransactionEntity> transactions = new ArrayList<>();
    private CustomerRequiredInfo customerRequiredInfo;
    private boolean adjustableQuantity;
    private String title;

}
