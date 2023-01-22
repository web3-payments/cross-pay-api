package com.cross.chain.payment.model;

import lombok.Data;

@Data
public class CustomerRequiredInfoDTO {

    private boolean name;
    private boolean email;
    private boolean phoneNumber;
    private boolean shippingAddress;

}
