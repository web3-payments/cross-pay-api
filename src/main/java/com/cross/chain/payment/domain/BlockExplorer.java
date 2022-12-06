package com.cross.chain.payment.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "block-explorer")
public class BlockExplorer {

    @Id
    private String id;
    private String name;
    private String transactionLink;
}
