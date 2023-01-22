package com.cross.chain.payment.controller.customer;

import com.cross.chain.payment.model.Customer;
import com.cross.chain.payment.model.ProductResponse;
import com.cross.chain.payment.exception.UserNotFoundException;
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

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Tag(name = "customers", description = "User Customer Operations")
public interface CustomerApiController {
    String CUSTOMER = "/user/{address}/customer";

    @Operation(summary = "create a customer given the user", tags={ "customers" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = MULTIPART_FORM_DATA_VALUE)),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity createCustomer(@Parameter(in = ParameterIn.PATH, description = "address that identify the user" ,schema=@Schema())@PathVariable(value = "address") String address,
                                 @RequestBody Customer customer) throws UserNotFoundException;

    @Operation(summary = "get all customer given the user", tags={ "customers" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity<List<Customer>> getAllCustomers(@Parameter(in = ParameterIn.PATH, description = "address that identify the user" ,schema=@Schema()) @PathVariable(value = "address") String address) throws UserNotFoundException;
}
