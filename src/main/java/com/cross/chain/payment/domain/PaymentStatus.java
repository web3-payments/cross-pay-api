package com.cross.chain.payment.domain;

import com.cross.chain.payment.dto.PaymentRequest;

import java.util.Arrays;

public enum PaymentStatus {

    CREATED("CREATED"),
    PAID("PAID"),
    CANCELLED("CANCELLED");

    private String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    public boolean isFinalStatus(){
        return Arrays.asList(PAID, CANCELLED).contains(valueOf(value));
    }

}