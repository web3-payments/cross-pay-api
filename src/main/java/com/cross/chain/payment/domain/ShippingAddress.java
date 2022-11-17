package com.cross.chain.payment.domain;

import lombok.Data;

@Data
public class ShippingAddress {

    private String address;
    private String city;
    private String country;
    private String state;
    private String zipCode;

}
