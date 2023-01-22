package com.cross.chain.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
public class Cryptocurrency {

    @JsonProperty("id")
    private String id;
    @JsonProperty("address")
    private String address;
    @JsonProperty("nativeToken")
    private boolean nativeToken;
    @JsonProperty("network")
    private NetworkDTO network;
    @JsonProperty("name")
    private String name;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("decimals")
    private int decimals;
    @JsonProperty("blockExplorer")
    private BlockExplorer blockExplorer;
    @JsonProperty("smartContract")
    private SmartContract smartContract;
}