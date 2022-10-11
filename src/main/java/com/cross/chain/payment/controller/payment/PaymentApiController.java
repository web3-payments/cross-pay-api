package com.cross.chain.payment.controller.payment;

import com.cross.chain.payment.model.PaymentRequest;
import com.cross.chain.payment.model.PaymentResponse;
import com.cross.chain.payment.model.PaymentType;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Tag(name = "payments", description = "Payment Operations")
public interface PaymentApiController {

    @Operation(summary = "create a payment", tags={ "payments" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentRequest.class))),
        @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
        @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity<PaymentResponse> createPayment(@Parameter(in = ParameterIn.DEFAULT, description = "Created payment object", schema=@Schema()) @RequestBody PaymentRequest body);


    @Operation(summary = "get all payments with filter", description = "", tags={ "payments" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentRequest.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity<PaymentRequest> paymentByHash(@Parameter(in = ParameterIn.PATH, description = "hash that identify the payment" ,schema=@Schema())@PathVariable(value = "paymentHash") String paymentHash);

    @Operation(summary = "get all payments with filter", description = "(INTERNAL USAGE ONLY)", tags={ "payments" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentRequest.class))),
        @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
        @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity<PaymentRequest> getAllPayment(@Parameter(in = ParameterIn.QUERY, description = "accountId that belongs the payments" ,schema=@Schema())@RequestParam(value = "accountId", required = false) String accountId, @Parameter(in = ParameterIn.QUERY, description = "Payment type that need to be considered for filter" ,schema=@Schema()) PaymentType paymentType);


    @Operation(summary = "get a payment by accountId", tags={ "payments" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentRequest.class))),
        @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
        @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity<PaymentRequest> getAllPaymentGivenAccountAndFilters(@Parameter(in = ParameterIn.QUERY, description = "accountId that belongs the payments" ,required=true,schema=@Schema()) String accountId, @Parameter(in = ParameterIn.QUERY, description = "Payment type that need to be considered for filter" ,schema=@Schema()) PaymentType paymentType);

}

