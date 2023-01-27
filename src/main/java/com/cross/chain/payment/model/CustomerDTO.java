package com.cross.chain.payment.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerDTO {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private ShippingAddressDTO shippingAddress;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}