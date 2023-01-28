package com.cross.chain.payment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CustomerInfo
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoDTO {
  private String name;
  private String email;
  private String phoneNumber;
  private ShippingAddressDTO shippingAddress;
}
