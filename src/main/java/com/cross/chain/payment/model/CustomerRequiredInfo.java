package com.cross.chain.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * CustomerRequiredInfo
 */
@Data
@Builder
@Validated
public class CustomerRequiredInfo   {

  @Schema(example = "true")
  @JsonProperty("name")
  private boolean name;

  @Schema(example = "true")
  @JsonProperty("email")
  private boolean email;

  @Schema(example = "true")
  @JsonProperty("phoneNumber")
  private boolean phoneNumber;

  @Schema(example = "true")
  @JsonProperty("shippingAddress")
  private boolean shippingAddress;

}
