package com.cross.chain.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * CustomerInfo
 */
@Data
public class CustomerInfoDTO {
  private String name;
  private String email;
  private String phoneNumber;
  private ShippingAddressDTO shippingAddress;
}
