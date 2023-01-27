package com.cross.chain.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class Customer {
    @JsonProperty("id")
    private String id;
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
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;
}
