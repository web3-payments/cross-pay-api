package com.cross.chain.payment.controller.product;

import com.cross.chain.payment.dto.ProductRequest;
import com.cross.chain.payment.dto.ProductResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Tag(name = "products", description = "User Products Operations")
public interface ProductApiController {

    String PRODUCT = "/user/{address}/product";

    @Operation(summary = "create a product given the user", tags={ "products" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = MULTIPART_FORM_DATA_VALUE)),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity createProductWithImage(@Parameter(in = ParameterIn.PATH, description = "address that identify the user" ,schema=@Schema())@PathVariable(value = "address") String address,
                                          @RequestPart("image") MultipartFile file, ProductRequest productRequest) throws UserNotFoundException, IOException;    @Operation(summary = "create a product given the user", tags={ "products" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = MULTIPART_FORM_DATA_VALUE)),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity createProduct(@Parameter(in = ParameterIn.PATH, description = "address that identify the user" ,schema=@Schema())@PathVariable(value = "address") String address,
                                          @RequestBody ProductRequest productRequest) throws UserNotFoundException;

    @Operation(summary = "get all products given the user", tags={ "products" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity<List<ProductResponse>> getAllProducts(@Parameter(in = ParameterIn.PATH, description = "address that identify the user" ,schema=@Schema()) @PathVariable(value = "address") String address) throws UserNotFoundException;

}
