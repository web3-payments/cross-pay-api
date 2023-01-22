package com.cross.chain.payment.persistence;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document(collection = "customer")
class CustomerEntity {

    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private ShippingAddress shippingAddress;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

}
