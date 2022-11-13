package com.cross.chain.payment.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "productsPayment")
public class ProductsPayment {

    @DBRef
    private Product product;

    private int quantity;

}
