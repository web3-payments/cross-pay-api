package com.cross.chain.payment.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "smart-contract")
public class SmartContract {

    @Id
    private String id;
    @DBRef
    private Network network;
    private String address;

}
