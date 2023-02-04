package com.cross.chain.payment.model.enums;

import java.util.Arrays;

public enum InvoiceStatus {

    AWAITING_PAYMENT("AWAITING_PAYMENT"),
    PAID("PAID"),
    DEACTIVATED("DEACTIVATED");

    private String value;

    InvoiceStatus(String value) {
        this.value = value;
    }

    public boolean isFinalStatus(){
        return Arrays.asList(PAID, DEACTIVATED).contains(valueOf(value));
    }

}