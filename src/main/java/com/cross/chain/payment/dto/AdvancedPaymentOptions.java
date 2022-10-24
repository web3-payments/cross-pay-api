package com.cross.chain.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * AdvancedPaymentOptions
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class AdvancedPaymentOptions   {

  @JsonProperty("multipleWallet")
  @Valid
  private List<WalletPayment> multipleWallet;

}
