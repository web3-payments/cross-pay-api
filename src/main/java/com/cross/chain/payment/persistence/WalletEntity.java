package com.cross.chain.payment.persistence;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "wallet")
class WalletEntity {

    @Id
    private String id;
    private String name;
    private String address;
    private String blockchain;
    private String chainId;
    private long createdAt;

}