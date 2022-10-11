package com.cross.chain.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * CustomerRequiredInfo
 */
@Data
@Builder
@Validated
public class CustomerRequiredInfo   {

  @Schema(example = "true")
  @JsonProperty("name")
  private Boolean name;

  @Schema(example = "true")
  @JsonProperty("email")
  private Boolean email;

  @Schema(example = "true")
  @JsonProperty("number")
  private Boolean number;

  @Schema(example = "true")
  @JsonProperty("shippingAddress")
  private Boolean shippingAddress;

}
