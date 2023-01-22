package com.cross.chain.payment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * SmartContractDTO
 */
@Data
@Builder
public class SmartContractDTO {
    private NetworkDTO network;
    private String address;
}