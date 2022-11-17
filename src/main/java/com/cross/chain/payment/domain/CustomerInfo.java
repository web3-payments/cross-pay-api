package com.cross.chain.payment.domain;

import lombok.Data;

@Data
public class CustomerInfo {

    private String name;
    private String email;
    private String phoneNumber;
    private ShippingAddress shippingAddress;

}
