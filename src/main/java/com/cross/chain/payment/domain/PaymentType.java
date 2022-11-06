package com.cross.chain.payment.domain;

/**
 * Payment types
 */
public enum PaymentType {

    INVOICE("INVOICE"),
    SUBSCRIPTION("SUBSCRIPTION"),
    PAYMENT_LINK("PAYMENT_LINK"),
    FLEXI_PAYMENT("FLEXI_PAYMENT");

  private String value;

  PaymentType(String value) {
    this.value = value;
  }

}
