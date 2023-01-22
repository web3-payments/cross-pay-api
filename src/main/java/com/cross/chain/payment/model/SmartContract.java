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
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class SmartContract {

    @JsonProperty("network")
    private Network network;
    @JsonProperty("address")
    private String address;

}
