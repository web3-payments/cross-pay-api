package com.cross.chain.payment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BlockExplorerDTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlockExplorerDTO {
    private String name;
    private String transactionLink;
}
