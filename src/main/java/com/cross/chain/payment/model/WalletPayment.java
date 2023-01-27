package com.cross.chain.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * WalletPayment
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class WalletPayment {

  @Schema(example = "0xfb542204Ed21212258a8DD6288C96674585382B7")
  @JsonProperty("address")
  private String address;

  @Schema(example = "50")
  @JsonProperty("share")
  private Integer share;

}
