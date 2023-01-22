package com.cross.chain.payment.model;

import lombok.Data;
import org.bson.types.Binary;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class ProductDTO {

    private String id;
    private String name;
    private BigDecimal price;
    private String description;
    private int totalSupply;
    private CryptocurrencyDTO cryptocurrency;
    private Binary image;
    private Instant createdAt;
    private Instant updatedAt;

}