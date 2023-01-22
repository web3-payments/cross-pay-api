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
@Validated
public class ShippingAddress {

    @Schema(example = "Street 12")
    @JsonProperty("address")
    private String address;

    @Schema(example = "San Francisco")
    @JsonProperty("city")
    private String city;

    @Schema(example = "Brazil")
    @JsonProperty("country")
    private String country;

    @Schema(example = "state")
    @JsonProperty("state")
    private String state;

    @Schema(example = "044584452")
    @JsonProperty("zipCode")
    private String zipCode;

}