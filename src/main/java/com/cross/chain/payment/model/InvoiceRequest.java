package com.cross.chain.payment.model;

import com.cross.chain.payment.model.enums.InvoiceStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * InvoiceRequest
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class InvoiceRequest {

  @Schema(example = "507f191e810c19729de860ea")
  @JsonProperty("id")
  private String id;

  @Schema(example = "Name of what is being offered")
  @JsonProperty("title")
  private String title;

  @Schema(example = "Invoice memo")
  @JsonProperty("memo")
  private String memo;

  @Schema(example = "Due date")
  @JsonProperty("dueDate")
  private String dueDate;

  @Schema(example = "Customer")
  @JsonProperty("customer")
  private Customer customer;

  @JsonProperty("hash")
  private String hash;
  @JsonProperty("uuid")
  private UUID uuid;

  @JsonProperty("createdAt")
  private String createdAt;

  @Valid
  @Schema(example = "55.34", description = "")
  @JsonProperty("amount")
  private BigDecimal amount;

  @Valid
  @JsonProperty("cryptocurrency")
  private Cryptocurrency cryptocurrency;

  @Schema(example = "false", description = "If true minAmount must be filled")
  @JsonProperty("minInvoice")
  private Boolean minInvoice;

  @Schema(example = "true", description = "If true minAmount and maxAmount must be filled")
  @JsonProperty("invoiceLimits")
  private Boolean invoiceLimits;

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
  private List<ProductInvoiceRequest> products;

  @Valid
  @Schema(example = "0x4279953514f0009c5cb371df4d530f6fee0ede17")
  @JsonProperty("creditAddress")
  private String creditAddress;

  @Valid
  @Schema(example = "0x4279953514f0009c5cb371df4d530f6fee0ede17")
  @JsonProperty("userAddress")
  private String userAddress;

  @JsonProperty("user") //TODO: review what information should be returned in here, cause is used on the invoicelink
  private UserRequest user;

  @JsonProperty("invoiceStatus")
  @Schema(hidden = true)
  private InvoiceStatus invoiceStatus;

  @JsonProperty("invoiceLink")
  @Schema(example = "https://buy.crosspay.com/test_28oaGzarrdlx6Pe288", description = "Required if invoice")
  private String invoiceLink;

}
