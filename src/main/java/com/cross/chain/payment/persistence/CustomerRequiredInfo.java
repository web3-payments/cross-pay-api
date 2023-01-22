package com.cross.chain.payment.persistence;

import lombok.Data;

@Data
class CustomerRequiredInfo {

    private boolean name;
    private boolean email;
    private boolean phoneNumber;
    private boolean shippingAddress;

}
