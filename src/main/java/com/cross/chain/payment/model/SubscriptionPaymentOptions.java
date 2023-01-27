package com.cross.chain.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * If the paymentType is Subscription
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "If the paymentType is Subscription")
@Validated
public class SubscriptionPaymentOptions   {

  @Schema(example = "25-09-2022")
  @JsonProperty("startDate")
  private String startDate;

  @Schema(example = "25-09-2023")
  @JsonProperty("endDate")
  private String endDate;

}