package com.cross.chain.payment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProductPaymentRequest
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPaymentDTO {
    private ProductDTO product;
    private int quantity;
}