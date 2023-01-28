package com.cross.chain.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * ShippingAddress
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShippingAddressDTO {

    private String address;
    private String city;
    private String country;
    private String state;
    private String zipCode;

}