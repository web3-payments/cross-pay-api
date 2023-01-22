package com.cross.chain.payment.persistence;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
class CustomerInfo {

    private String name;
    private String email;
    private String phoneNumber;
    private ShippingAddress shippingAddress;

}
