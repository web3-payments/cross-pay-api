package com.cross.chain.payment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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