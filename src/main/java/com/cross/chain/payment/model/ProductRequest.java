package com.cross.chain.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * ProductRequest
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class ProductRequest {

  @Schema(example = "Product Name")
  @JsonProperty("name")
  private String name;

  @Valid
  @Schema(example = "55.34")
  @JsonProperty("price")
  private BigDecimal price;

  @Schema(example = "Product Description")
  @JsonProperty("description")
  private String description;

  @Schema(example = "10")
  @JsonProperty("totalSupply")
  private int totalSupply;

  @Schema(example = "ETH")
  @JsonProperty("cryptocurrency")
  private CryptocurrencyDTO cryptocurrency;

  @JsonProperty("cryptocurrencyId")
  private String cryptocurrencyId;

}