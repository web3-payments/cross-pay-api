package com.cross.chain.payment.domain;

import java.util.Arrays;

public enum PaymentStatus {

    CREATED("CREATED"),
    PAID("PAID"),
    CANCELLED("CANCELLED"),

    ACTIVATED("ACTIVATED"),

    DEACTIVATED("DEACTIVATED");

    private String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    public boolean isFinalStatus(){
        return Arrays.asList(PAID, CANCELLED, DEACTIVATED).contains(valueOf(value));
    }

}