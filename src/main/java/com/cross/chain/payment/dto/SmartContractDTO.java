package com.cross.chain.payment.dto;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class SmartContractDTO {

    @JsonProperty("network")
    private NetworkDTO network;
    @JsonProperty("address")
    private String address;

}
