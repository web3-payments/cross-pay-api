package com.cross.chain.payment.domain;

import lombok.Data;

@Data
public class CustomerRequiredInfo {

    private boolean name;
    private boolean email;
    private boolean phoneNumber;
    private boolean shippingAddress;

}
