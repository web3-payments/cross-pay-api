package com.cross.chain.payment.controller.product;

import com.cross.chain.payment.domain.PaymentType;
import com.cross.chain.payment.dto.PaymentRequest;
import com.cross.chain.payment.dto.PaymentResponse;
import com.cross.chain.payment.dto.ProductRequest;
import com.cross.chain.payment.dto.ProductResponse;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "products", description = "User Products Operations")
public interface ProductApiController {

    @Operation(summary = "create a product given the user", tags={ "products" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE,  schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity<PaymentResponse> createProduct(@Parameter(in = ParameterIn.PATH, description = "address that identify the user" ,schema=@Schema())@PathVariable(value = "address") String address,
                                                  @Parameter(in = ParameterIn.DEFAULT, description = "Created payment object", schema=@Schema()) @RequestBody ProductRequest body);

    @Operation(summary = "get all products given the user", tags={ "products" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity<PaymentRequest> getAllProducts(@Parameter(in = ParameterIn.QUERY, description = "address that identify the user" ,schema=@Schema())@RequestParam(value = "address") String address);

}
