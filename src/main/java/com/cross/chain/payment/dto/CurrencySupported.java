package com.cross.chain.payment.dto;

/**
 * Crypto currency supported
 */
public enum CurrencySupported {

    //TODO: MIGRATE TO A DATABASE THIS INFORMATION
    SOL("SOL"),
    ETH("ETH"),
    BTC("BTC");

  private String value;

  CurrencySupported(String value) {
    this.value = value;
  }

}
