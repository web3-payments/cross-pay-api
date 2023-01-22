package com.cross.chain.payment.model;

import lombok.Data;

import java.time.Instant;

@Data
public class CustomerDTO {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private ShippingAddressDTO shippingAddress;
    private Instant createdAt;
    private Instant updatedAt;
}