package com.cross.chain.payment.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cryptocurrency")
public class Cryptocurrency {

    @Id
    private String id;
    private String address;
    private boolean nativeToken;
    private String chainId;
    private String name;
    private String symbol;
    private int decimals;
    @DBRef
    private BlockExplorer blockExplorer;

}
