package com.cross.chain.payment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorModel {

    @ApiModelProperty(required = true, value = "HTTP error code")
    private int status;

    @ApiModelProperty(required = true, value = "HTTP error text")
    private String error;

    @ApiModelProperty(required = true, value = "Error reason")
    private String message;

    /**
     * {
     *   "timestamp": "2022-10-07T20:18:09.463+00:00",
     *   "status": 400,
     *   "error": "Bad Request",
     *   "path": "/api/v1/payment"
     * }
     */

}
