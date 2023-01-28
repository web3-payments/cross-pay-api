package com.cross.chain.payment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequiredInfoDTO {

    private boolean name;
    private boolean email;
    private boolean phoneNumber;
    private boolean shippingAddress;

}
