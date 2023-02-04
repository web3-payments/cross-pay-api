package com.cross.chain.payment.model;

import com.cross.chain.payment.model.enums.InvoiceStatus;
import com.cross.chain.payment.model.enums.InvoiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * InvoiceRequest
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceRequestDTO {
  private String id;
  private String title;
  private String memo;
  private String dueDate;
  private Customer customer;
  private InvoiceType invoiceType;
  private String hash;
  private UUID uuid;
  private LocalDateTime createdAt;
  private BigDecimal amount;
  private CryptocurrencyDTO cryptocurrency;
  private Boolean minInvoice;
  private Boolean invoiceLimits;
  private BigDecimal minAmount;
  private BigDecimal maxAmount;
  private List<ProductPaymentRequestDTO> products;
  private TransactionDTO transaction;
  private boolean adjustableQuantity;
  private String creditAddress;
  private String userAddress;
  private UserDTO user;
  private InvoiceStatus invoiceStatus;
  private String invoiceLink;
}