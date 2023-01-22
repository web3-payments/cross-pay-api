package com.cross.chain.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * ShippingAddress
 */
@Data
@Builder
public class ShippingAddressDTO {

    private String address;
    private String city;
    private String country;
    private String state;
    private String zipCode;

}