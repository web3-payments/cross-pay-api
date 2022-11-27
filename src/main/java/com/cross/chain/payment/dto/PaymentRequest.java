package com.cross.chain.payment.dto;

import com.cross.chain.payment.domain.PaymentStatus;
import com.cross.chain.payment.domain.PaymentType;
import com.cross.chain.payment.validators.EnumValuePattern;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * PaymentRequest
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class PaymentRequest   {

  @Schema(example = "507f191e810c19729de860ea")
  @JsonProperty("id")
  private String id;

  @Schema(example = "Name of what is being offered")
  @JsonProperty("title")
  private String title;

  @Schema(example = "Detail on that they are paying for")
  @JsonProperty("description")
  private String description;

  @Valid
  @EnumValuePattern(regexp = ("INVOICE|SUBSCRIPTION|PAYMENT_LINK|FLEXI_PAYMENT"), message = "Invalid payment type provided, only ")
  @Schema(example = "PAYMENT_LINK")
  @JsonProperty("paymentType")
  private PaymentType paymentType;

  @JsonProperty("hash")
  private String hash;

  @JsonProperty("createdAt")
  private Instant createdAt;

  @Valid
  @Schema(example = "55.34", description = "")
  @JsonProperty("amount")
  private BigDecimal amount;

  @Valid
  @JsonProperty("cryptocurrency")
  private CryptocurrencyDto cryptocurrency;

  @Schema(example = "false", description = "If true minAmount must be filled")
  @JsonProperty("minPayment")
  private Boolean minPayment;

  @Schema(example = "true", description = "If true minAmount and maxAmount must be filled")
  @JsonProperty("paymentLimits")
  private Boolean paymentLimits;

  @Valid
  @Schema(example = "0.5", description = "")
  @JsonProperty("minAmount")
  private BigDecimal minAmount;

  @Valid
  @Schema(example = "10")
  @JsonProperty("maxAmount")
  private BigDecimal maxAmount;

  @Valid
  @JsonProperty("products")
  private List<ProductPaymentRequest> products;

  @Valid
  @JsonProperty("adjustableQuantity")
  private boolean adjustableQuantity;

  @Valid
  @Schema(example = "0x4279953514f0009c5cb371df4d530f6fee0ede17")
  @JsonProperty("creditAddress")
  private String creditAddress;

  @Valid
  @Schema(example = "0x4279953514f0009c5cb371df4d530f6fee0ede17")
  @JsonProperty("userAddress")
  private String userAddress;

  @JsonProperty("paymentStatus")
  @Schema(hidden = true)
  private PaymentStatus paymentStatus;

  @Valid
  @JsonProperty("customerRequiredInfo")
  private CustomerRequiredInfo customerRequiredInfo;

  @Valid
  @JsonProperty("customerInfo")
  private CustomerInfoDto customerInfo;

  @JsonProperty("paymentLink")
  @Schema(example = "https://buy.crosspay.com/test_28oaGzarrdlx6Pe288", description = "Required if paymentType is PaymentLink")
  private String paymentLink;

  @Schema(example = "false", description = "If true AdvancedPaymentOptions must be filled")
  @JsonProperty("splitPayment")
  private Boolean splitPayment;

  @Valid
  @JsonProperty("advancedPaymentOptions")
  private AdvancedPaymentOptions advancedPaymentOptions;

  @Valid
  @JsonProperty("subscriptionPaymentOptions")
  private SubscriptionPaymentOptions subscriptionPaymentOptions;

}
