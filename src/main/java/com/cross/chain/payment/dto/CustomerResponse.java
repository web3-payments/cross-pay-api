package com.cross.chain.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * CustomerResponse
 */
@Data
@Builder
@Validated
public class CustomerResponse   {

  @Schema(example = "507f191e810c19729de860ea")
  @JsonProperty("id")
  private String id;

  @Schema(example = "Customer Name")
  @JsonProperty("name")
  private String name;

  @Schema(example = "john@email.com")
  @JsonProperty("email")
  private String email;

}
