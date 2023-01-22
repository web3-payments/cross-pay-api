package com.cross.chain.payment.persistence;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "block-explorer")
class BlockExplorerEntity {
    @Id
    private String id;
    private String name;
    private String transactionLink;
}