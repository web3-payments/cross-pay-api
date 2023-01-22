package com.cross.chain.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;

@Data
@Builder
@Validated
public class Customer {

    @Schema(example = "Customer Name")
    @JsonProperty("name")
    private String name;

    @Schema(example = "john@email.com")
    @JsonProperty("email")
    private String email;

    @Schema(example = "john@email.com")
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("shippingAddress")
    private ShippingAddress shippingAddress;
    private Instant createdAt;
    private Instant updatedAt;
}
