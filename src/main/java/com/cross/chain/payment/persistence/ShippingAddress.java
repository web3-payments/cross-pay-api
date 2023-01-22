package com.cross.chain.payment.persistence;

import lombok.Data;

@Data
class ShippingAddress {

    private String address;
    private String city;
    private String country;
    private String state;
    private String zipCode;

}
