package com.cross.chain.payment.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class WalletDTO {
    private String name;
    private String address;
    private String blockchain;
    private String chainId;
    private long createdAt;
}