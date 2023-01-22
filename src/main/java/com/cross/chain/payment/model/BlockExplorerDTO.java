package com.cross.chain.payment.model;

import lombok.Builder;
import lombok.Data;

/**
 * BlockExplorerDTO
 */
@Data
@Builder
public class BlockExplorerDTO {
    private String name;
    private String transactionLink;
}
