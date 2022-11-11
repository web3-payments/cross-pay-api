package com.cross.chain.payment.domain;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Document(collection = "product")
public class Product {

    @Id
    private String id;
    private String name;
    private BigDecimal price;
    private String description;
    private int totalSupply;
    private String token;
    private Binary image;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

}