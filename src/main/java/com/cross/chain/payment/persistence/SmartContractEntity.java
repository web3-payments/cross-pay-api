package com.cross.chain.payment.persistence;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "smart-contract")
class SmartContractEntity {

    @Id
    private String id;
    @DBRef
    private NetworkEntity network;
    private String address;

}
