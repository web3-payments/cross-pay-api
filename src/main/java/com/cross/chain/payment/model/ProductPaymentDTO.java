package com.cross.chain.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

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