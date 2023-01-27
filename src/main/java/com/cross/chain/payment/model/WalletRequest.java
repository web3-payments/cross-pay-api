package com.cross.chain.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * AccountRequest
 */
@Data
@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class WalletRequest {
  
  @JsonProperty("name")
  @Schema(example = "account name")
  private String name;

  @JsonProperty("address")
  @Schema(example = "0x4279953514f0009c5cb371df4d530f6fee0ede17")
  private String address;

  @JsonProperty("blockchain")
  @Schema(example = "Ethereum")
  private String blockchain;

  @JsonProperty("chainId")
  @Schema(example = "1")
  private String chainId;

  @JsonProperty("createdAt")
  @Schema(example = "1667408529711")
  private long createdAt;
  
}
