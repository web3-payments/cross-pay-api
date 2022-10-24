package com.cross.chain.payment.domain;

import com.cross.chain.payment.dto.CurrencySupported;
import com.cross.chain.payment.dto.PaymentType;
import lombok.Data;
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
    //TODO: this information will be filled when added security with access token to identify the user
    private String userIdentifier;
    private String hash;
    @Indexed
    private Instant createdAt;
    private BigDecimal amount;
    private CurrencySupported currency;
    private PaymentType paymentType;
    private String title;

}
