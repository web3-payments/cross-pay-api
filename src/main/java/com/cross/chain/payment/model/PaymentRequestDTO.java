package com.cross.chain.payment.model;

import com.cross.chain.payment.model.enums.PaymentStatus;
import com.cross.chain.payment.model.enums.PaymentType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * PaymentRequest
 */
@Data
@Builder
public class PaymentRequestDTO {
  private String id;
  private String title;
  private String description;
  private PaymentType paymentType;
  private String hash;
  private LocalDateTime createdAt;
  private BigDecimal amount;
  private CryptocurrencyDTO cryptocurrency;
  private Boolean minPayment;
  private Boolean paymentLimits;
  private BigDecimal minAmount;
  private BigDecimal maxAmount;
  private List<ProductPaymentRequestDTO> products;
  private List<TransactionDTO> transactions;
  private boolean adjustableQuantity;
  private String creditAddress;
  private String userAddress;
  private UserDTO user;
  private PaymentStatus paymentStatus;
  private CustomerRequiredInfoDTO customerRequiredInfo;
  private String paymentLink;
  private Boolean splitPayment;
  private AdvancedPaymentOptions advancedPaymentOptions;
  private SubscriptionPaymentOptions subscriptionPaymentOptions;
}