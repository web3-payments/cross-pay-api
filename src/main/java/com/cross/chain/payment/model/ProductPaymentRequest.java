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
@Validated
public class ProductPaymentRequest {

    @Valid
    @JsonProperty("item")
    private ProductResponse product;

    private int quantity;

}
