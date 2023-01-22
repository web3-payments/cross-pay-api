package com.cross.chain.payment.persistence;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
class ProductsPayment {

    @DBRef
    private ProductEntity product;

    private int quantity;

}
