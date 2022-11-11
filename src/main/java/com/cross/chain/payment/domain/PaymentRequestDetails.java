package com.cross.chain.payment.domain;

import com.cross.chain.payment.dto.CurrencySupported;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

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
    private CurrencySupported currency;
    private PaymentType paymentType;
    private PaymentStatus paymentStatus;
    private String title;

}
