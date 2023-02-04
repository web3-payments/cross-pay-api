package com.cross.chain.payment.persistence;

import com.cross.chain.payment.model.Customer;
import com.cross.chain.payment.model.enums.InvoiceStatus;
import com.cross.chain.payment.model.enums.InvoiceType;
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
import java.util.UUID;

@Data
@Document(collection = "invoice-request")
class InvoiceRequestEntity {

    @Id
    private String id;
    private String memo;
    private String dueDate;
    private Customer customer;
    private String creditAddress;
    private String userAddress;
    private String hash;
    private UUID uuid;
    private String invoiceLink;
    @Indexed
    @CreatedDate
    private LocalDateTime createdAt;
    private BigDecimal amount;
    @DBRef
    private UserEntity user;
    @DBRef
    private CryptocurrencyEntity cryptocurrency;
    private InvoiceType invoiceType;
    private InvoiceStatus invoiceStatus;
    private List<ProductsPayment> products = new ArrayList<>();
    @DBRef
    private TransactionEntity transaction;

    private String title;

}
