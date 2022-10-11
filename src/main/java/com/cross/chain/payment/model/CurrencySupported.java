package com.cross.chain.payment.model;

/**
 * Crypto currency supported
 */
public enum CurrencySupported {

    //TODO: MIGRATE TO A DATABASE THIS INFORMATION
    SOL("SOL"),
    ETHER("ETHER"),
    BTC("BTC");

  private String value;

  CurrencySupported(String value) {
    this.value = value;
  }

}
