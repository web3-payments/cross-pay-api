package com.cross.chain.payment.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@Validated
public class CryptocurrencyDTO {

    private String id;
    private String address;
    private boolean nativeToken;
    private String chainId;
    private String name;
    private String symbol;
    private int decimals;

}