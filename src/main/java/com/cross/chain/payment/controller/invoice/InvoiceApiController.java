package com.cross.chain.payment.controller.invoice;

import com.cross.chain.payment.exception.InvoiceRequestNotFound;
import com.cross.chain.payment.model.InvoiceConfirmationRequest;
import com.cross.chain.payment.model.InvoiceRequest;
import com.cross.chain.payment.model.InvoiceResponse;
import com.cross.chain.payment.model.Transaction;
import com.cross.chain.payment.model.enums.InvoiceType;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Tag(name = "invoices", description = "Invoice Operations")
public interface InvoiceApiController {

    String INVOICE = "/invoice";
    String INVOICE_HASH = "/invoice/{invoiceHash}";
    String INVOICE_HASH_TRANSACTION = "/invoice/{invoiceHash}/transaction";
    String INVOICE_HASH_CONFIRMATION = "/invoice/{invoiceHash}/confirmation";
    String INVOICE_HASH_CANCELLATION = "/invoice/{invoiceHash}/cancellation";
    String INVOICE_FIND_BY_USER_ADDRESS = "/invoice/findByUserAddress";

    @Operation(summary = "create a invoice", tags={ "invoices" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = InvoiceRequest.class))),
        @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
        @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity<InvoiceResponse> createInvoice(@Parameter(in = ParameterIn.DEFAULT, description = "Created invoice object", schema=@Schema()) @RequestBody InvoiceRequest body);


    @Operation(summary = "get all invoices with filter", description = "", tags={ "invoices" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = InvoiceRequest.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied")})
    ResponseEntity<InvoiceRequest> invoiceByHash(@Parameter(in = ParameterIn.PATH, description = "hash that identify the invoice" ,schema=@Schema())@PathVariable(value = "invoiceHash") String invoiceHash) throws InvoiceRequestNotFound;

    @Operation(summary = "get all transaction with filter", description = "", tags={ "invoices" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = InvoiceRequest.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied")})
    ResponseEntity<List<Transaction>> transactionByInvoiceHash(@Parameter(in = ParameterIn.PATH, description = "hash that identify the invoice" ,schema=@Schema())@PathVariable(value = "invoiceHash") String invoiceHash) throws InvoiceRequestNotFound;

    @Operation(summary = "Update invoice given a hash", description = "", tags={ "invoices" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = InvoiceRequest.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "Invoice not found") })
    ResponseEntity<InvoiceRequest> updateInvoiceByHash(@Parameter(in = ParameterIn.PATH, description = "hash that identify the invoice" ,schema=@Schema()) @PathVariable(value = "invoiceHash") String invoiceHash, @RequestBody InvoiceRequest body) throws InvoiceRequestNotFound;

    @Operation(summary = "Invoice confirmation", description = "", tags={ "invoices" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = InvoiceRequest.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity invoiceConfirmation(@Parameter(in = ParameterIn.PATH, description = "hash that identify the invoice" ,schema=@Schema())@PathVariable(value = "invoiceHash") String invoiceHash,
                                                       @Parameter(in = ParameterIn.DEFAULT, description = "Created invoice object", schema=@Schema()) @RequestBody InvoiceConfirmationRequest body) throws InvoiceRequestNotFound;

    @Operation(summary = "Invoice cancellation", description = "", tags={ "invoices" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = InvoiceRequest.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity invoiceCancellation(@Parameter(in = ParameterIn.PATH, description = "hash that identify the invoice" ,schema=@Schema())@PathVariable(value = "invoiceHash") String invoiceHash) throws InvoiceRequestNotFound;

    @Operation(summary = "get all invoices with filter", description = "(INTERNAL USAGE ONLY)", tags={ "invoices" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = InvoiceRequest.class))),
        @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
        @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity<InvoiceRequest> getAllInvoice(@Parameter(in = ParameterIn.QUERY, description = "accountId that belongs the invoices" ,schema=@Schema())@RequestParam(value = "accountId", required = false) String accountId, @Parameter(in = ParameterIn.QUERY, description = "Invoice type that need to be considered for filter" ,schema=@Schema()) InvoiceType invoiceType);


    @Operation(summary = "get a invoice by User Address", tags={ "invoices" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InvoiceRequest.class))),
        @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
        @ApiResponse(responseCode = "404", description = "User not found") })
    ResponseEntity<List<InvoiceRequest>> getAllInvoiceGivenUserAddressAndFilters(@Parameter(in = ParameterIn.QUERY, description = "User address that owns the invoices" ,required=true,schema=@Schema()) String accountId, @Parameter(in = ParameterIn.QUERY, description = "Invoice type that need to be considered for filter" ,schema=@Schema()) InvoiceType invoiceType);

}

