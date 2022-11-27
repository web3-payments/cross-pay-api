package com.cross.chain.payment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * UserRequest
 */
@Data
@Builder
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest {

  @JsonProperty("firstName")
  @Schema(example = "Bob")
  private String firstName;

  @JsonProperty("lastName")
  @Schema(example = "John")
  private String lastName;

  @JsonProperty("companyName")
  @Schema(example = "Trixie")
  private String companyName;

  @JsonProperty("signerAddress")
  @Schema(example = "0x4279953514f0009c5cb371df4d530f6fee0ede17")
  private String signerAddress;

  @JsonProperty("signature")
  @Schema(example = "0xe536fea8d6b290ad737b6126ef290ea148d2dfbbf03fcc3cf3d0cf32eaa56ada23f71d25304ea9d1e5c7800bf5eb0dd149b9abdc03a77f8688f7747f95969fd81b")
  private String signature;

  @JsonProperty("email")
  @Schema(example = "john@email.com")
  private String email;

  @JsonProperty("phone")
  @Schema(example = "911255254")
  private String phone;

  @JsonProperty("image")
  private byte[] image;
  
  @Valid
  @JsonProperty("wallets")
  private List<WalletRequest> wallets;

}
