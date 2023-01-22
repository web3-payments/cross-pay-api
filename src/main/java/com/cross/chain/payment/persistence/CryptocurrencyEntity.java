package com.cross.chain.payment.persistence;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cryptocurrency")
class CryptocurrencyEntity {
    @Id
    private String id;
    private String address;
    private boolean nativeToken;
    @DBRef
    private NetworkEntity network;
    private String name;
    private String symbol;
    private int decimals;
    @DBRef
    private BlockExplorerEntity blockExplorer;
    @DBRef
    private SmartContractEntity smartContract;
}