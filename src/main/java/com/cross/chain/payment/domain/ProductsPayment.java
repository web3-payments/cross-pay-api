package com.cross.chain.payment.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class ProductsPayment {

    @DBRef
    private Product product;

    private int quantity;

}
