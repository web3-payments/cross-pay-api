package com.cross.chain.payment.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "network")
public class Network {

    @Id
    private String id;
    private long chainId;
    private String name;

}
