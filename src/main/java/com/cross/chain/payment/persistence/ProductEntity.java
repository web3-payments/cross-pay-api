package com.cross.chain.payment.persistence;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document(collection = "product")
class ProductEntity {

    @Id
    private String id;
    private String name;
    private BigDecimal price;
    private String description;
    private int totalSupply;
    @DBRef
    private CryptocurrencyEntity cryptocurrency;
    private Binary image;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

}