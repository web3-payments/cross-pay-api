package com.cross.chain.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private NetworkDTO network;
    private String name;
    private String symbol;
    private int decimals;
    private BlockExplorerDTO blockExplorer;
    private SmartContractDTO smartContract;
}