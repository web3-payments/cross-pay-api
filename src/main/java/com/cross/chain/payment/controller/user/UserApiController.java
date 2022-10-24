package com.cross.chain.payment.controller.user;

import com.cross.chain.payment.exception.UserNotFoundException;
import com.cross.chain.payment.dto.UserRequest;
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

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "user", description = "User Operations")
public interface UserApiController {

    String USER = "/user";
    String USER_ADDRESS = "/user/{address}";

    @Operation(summary = "Create user", description = "The user is created after logged in via Web3 Wallet.", tags={ "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserRequest.class))) })
    ResponseEntity<UserRequest> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "Created user object", schema=@Schema()) @Valid @RequestBody UserRequest body);

    @Operation(summary = "get all payments with filter", tags={ "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserRequest.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity<UserRequest> retrieveUser(@Parameter(in = ParameterIn.PATH, description = "User address that identify the user" ,schema=@Schema())@PathVariable(value = "address") String address) throws UserNotFoundException;

}
