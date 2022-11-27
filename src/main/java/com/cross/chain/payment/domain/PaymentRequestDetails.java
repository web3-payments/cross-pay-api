package com.cross.chain.payment.domain;

import com.cross.chain.payment.dto.CurrencySupported;
import com.cross.chain.payment.dto.CustomerRequiredInfo;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Document(collection = "payment-request")
public class PaymentRequestDetails {

    @Id
    private String id;
    private String creditAddress;
    private String userAddress;
    private String hash;
    private String paymentLink;
    @Indexed
    @CreatedDate
    private Instant createdAt;
    private BigDecimal amount;
    @DBRef
    private Cryptocurrency cryptocurrency;
    private PaymentType paymentType;
    private PaymentStatus paymentStatus;
    private List<ProductsPayment> products;
    private CustomerRequiredInfo customerRequiredInfo;
    private CustomerInfo customerInfo;
    private boolean adjustableQuantity;
    private String title;

}
