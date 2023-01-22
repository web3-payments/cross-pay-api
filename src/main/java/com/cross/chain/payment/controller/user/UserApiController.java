package com.cross.chain.payment.controller.user;

import com.cross.chain.payment.model.UserRequest;
import com.cross.chain.payment.model.WalletRequest;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Tag(name = "user", description = "User Operations")
public interface UserApiController {

    String USER = "/user";
    String USER_ADDRESS = "/user/{address}";

    String USER_WALLETS = "/user/{address}/wallet";

    @Operation(summary = "Create user", description = "The user is created after logged in via Web3 Wallet.", tags={ "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserRequest.class))) })
    ResponseEntity<UserRequest> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "Created user object", schema=@Schema()) @Valid @RequestBody UserRequest body);

    @Operation(summary = "Update user", description = "This can only be done by the logged in user.", tags={ "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation") })
    ResponseEntity<UserRequest> updateUser(@Parameter(in = ParameterIn.PATH, description = "username that need to be updated", required=true, schema=@Schema()) @PathVariable("address") String address,
                                    @Parameter(in = ParameterIn.DEFAULT, description = "Update an existent user", schema=@Schema()) @Valid @RequestBody UserRequest body) throws UserNotFoundException;

    @Operation(summary = "Upload a image to a given user", tags={ "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = MULTIPART_FORM_DATA_VALUE)),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity uploadImage(@Parameter(in = ParameterIn.PATH, description = "address that identify the user" ,schema=@Schema())@PathVariable(value = "address") String address,
                                          @RequestPart("image") MultipartFile file) throws UserNotFoundException, IOException;

    @Operation(summary = "get all user by address", tags={ "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserRequest.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity<UserRequest> retrieveUser(@Parameter(in = ParameterIn.PATH, description = "User address that identify the user" ,schema=@Schema())@PathVariable(value = "address") String address) throws UserNotFoundException;

    @Operation(summary = "get all user wallets by address", tags={ "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserRequest.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "User Wallets not found") })
    ResponseEntity<List<WalletRequest>> retrieveUserWallets(@Parameter(in = ParameterIn.PATH, description = "User address that identify the user" ,schema=@Schema())@PathVariable(value = "address") String address) throws UserNotFoundException;


}
