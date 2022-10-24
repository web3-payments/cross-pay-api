package com.cross.chain.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * AccountRequest
 */
@Data
@Builder
@Validated
public class AccountRequest {
  
  @JsonProperty("name")
  @Schema(example = "account name")
  private String name;

  @JsonProperty("address")
  @Schema(example = "0x4279953514f0009c5cb371df4d530f6fee0ede17")
  private String address;
  
}
