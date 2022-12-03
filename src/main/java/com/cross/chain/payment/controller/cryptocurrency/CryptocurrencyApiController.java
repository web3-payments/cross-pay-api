package com.cross.chain.payment.controller.cryptocurrency;

import com.cross.chain.payment.dto.CryptocurrencyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "cryptocurrency", description = "Supported Crypto Asset")
public interface CryptocurrencyApiController {

    String CRYPTOCURRENCY = "/cryptocurrency";

    @Operation(summary = "get all supported cryptocurrencies", tags={ "cryptocurrency" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CryptocurrencyDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied")})
    ResponseEntity<List<CryptocurrencyDTO>> getAllSupportedCryptocurrencies();
}
