package com.cross.chain.payment.controller.payment;

import com.cross.chain.payment.dto.PaymentConfirmationDTO;
import com.cross.chain.payment.dto.PaymentRequest;
import com.cross.chain.payment.dto.PaymentResponse;
import com.cross.chain.payment.domain.PaymentType;
import com.cross.chain.payment.exception.PaymentRequestNotFound;
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

import java.util.List;

import static org.springframework.http.MediaType.*;


@Tag(name = "payments", description = "Payment Operations")
public interface PaymentApiController {

    String PAYMENT = "/payment";
    String PAYMENT_HASH = "/payment/{paymentHash}";
    String PAYMENT_HASH_CONFIRMATION = "/payment/{paymentHash}/confirmation";
    String PAYMENT_HASH_CANCELLATION = "/payment/{paymentHash}/cancellation";
    String PAYMENT_FIND_BY_USER_ADDRESS = "/payment/findByUserAddress";

    @Operation(summary = "create a payment", tags={ "payments" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaymentRequest.class))),
        @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
        @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity<PaymentResponse> createPayment(@Parameter(in = ParameterIn.DEFAULT, description = "Created payment object", schema=@Schema()) @RequestBody PaymentRequest body);


    @Operation(summary = "get all payments with filter", description = "", tags={ "payments" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaymentRequest.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied")})
    ResponseEntity<PaymentRequest> paymentByHash(@Parameter(in = ParameterIn.PATH, description = "hash that identify the payment" ,schema=@Schema())@PathVariable(value = "paymentHash") String paymentHash) throws PaymentRequestNotFound;

    @Operation(summary = "Update payment given a hash", description = "", tags={ "payments" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaymentRequest.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "Payment not found") })
    ResponseEntity<PaymentRequest> updatePaymentByHash(@Parameter(in = ParameterIn.PATH, description = "hash that identify the payment" ,schema=@Schema()) @PathVariable(value = "paymentHash") String paymentHash, @RequestBody PaymentRequest body) throws PaymentRequestNotFound;

    @Operation(summary = "Payment confirmation", description = "", tags={ "payments" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaymentRequest.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity paymentConfirmation(@Parameter(in = ParameterIn.PATH, description = "hash that identify the payment" ,schema=@Schema())@PathVariable(value = "paymentHash") String paymentHash,
                                                       @Parameter(in = ParameterIn.DEFAULT, description = "Created payment object", schema=@Schema()) @RequestBody PaymentConfirmationDTO body) throws PaymentRequestNotFound;

    @Operation(summary = "Payment cancellation", description = "", tags={ "payments" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaymentRequest.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity paymentCancellation(@Parameter(in = ParameterIn.PATH, description = "hash that identify the payment" ,schema=@Schema())@PathVariable(value = "paymentHash") String paymentHash) throws PaymentRequestNotFound;

    @Operation(summary = "get all payments with filter", description = "(INTERNAL USAGE ONLY)", tags={ "payments" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = PaymentRequest.class))),
        @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
        @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity<PaymentRequest> getAllPayment(@Parameter(in = ParameterIn.QUERY, description = "accountId that belongs the payments" ,schema=@Schema())@RequestParam(value = "accountId", required = false) String accountId, @Parameter(in = ParameterIn.QUERY, description = "Payment type that need to be considered for filter" ,schema=@Schema()) PaymentType paymentType);


    @Operation(summary = "get a payment by User Address", tags={ "payments" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentRequest.class))),
        @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
        @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity<List<PaymentRequest>> getAllPaymentGivenUserAddressAndFilters(@Parameter(in = ParameterIn.QUERY, description = "User address that owns the payments" ,required=true,schema=@Schema()) String accountId, @Parameter(in = ParameterIn.QUERY, description = "Payment type that need to be considered for filter" ,schema=@Schema()) PaymentType paymentType);

}

