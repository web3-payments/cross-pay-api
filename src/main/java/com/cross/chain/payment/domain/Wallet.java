package com.cross.chain.payment.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "wallet")
public class Wallet {

    private String name;

    private String address;

    private String blockchain;

    private String chainId;

    private long createdAt;

}