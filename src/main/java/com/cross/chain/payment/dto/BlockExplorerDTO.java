package com.cross.chain.payment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * BlockExplorerDTO
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class BlockExplorerDTO {

    @JsonProperty("name")
    private String name;
    @JsonProperty("transactionLink")
    private String transactionLink;

}
