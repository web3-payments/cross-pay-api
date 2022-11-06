package com.cross.chain.payment.dto;

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
  @Schema(example = "55.34", description = "")
  @JsonProperty("price")
  private BigDecimal price;

  @Schema(example = "Product Description")
  @JsonProperty("description")
  private String description;

  @Valid
  @JsonProperty("image")
  private String image;

  @Schema(example = "10-10-2022 22:00:00")
  @JsonProperty("createdAt")
  private String createdAt;

  @Schema(example = "11-10-2022 22:00:00")
  @JsonProperty("updatedAt")
  private String updatedAt;

}