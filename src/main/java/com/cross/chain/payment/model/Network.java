package com.cross.chain.payment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class Network {
    private long chainId;
    private String name;
}
