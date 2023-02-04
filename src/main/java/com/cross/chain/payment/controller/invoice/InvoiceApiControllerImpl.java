package com.cross.chain.payment.controller.invoice;

import com.cross.chain.payment.exception.InvoiceRequestNotFound;
import com.cross.chain.payment.model.InvoiceConfirmationRequest;
import com.cross.chain.payment.model.InvoiceRequest;
import com.cross.chain.payment.model.InvoiceResponse;
import com.cross.chain.payment.model.Transaction;
import com.cross.chain.payment.model.enums.InvoiceType;
import com.cross.chain.payment.service.invoice.InvoiceProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class InvoiceApiControllerImpl implements InvoiceApiController {

    @Autowired
    private InvoiceProcessorService invoiceProcessorService;
    @Override
    @PostMapping(value = INVOICE, produces = { APPLICATION_JSON_VALUE }, consumes = { APPLICATION_JSON_VALUE })
    public ResponseEntity<InvoiceResponse> createInvoice(@Valid @RequestBody InvoiceRequest body) {
        return ResponseEntity.ok(invoiceProcessorService.processInvoiceRequest(body));
    }

    @Override
    @GetMapping(value = INVOICE_HASH, produces = { APPLICATION_JSON_VALUE })
    public ResponseEntity<InvoiceRequest> invoiceByHash(@PathVariable("invoiceHash") String invoiceHash) throws InvoiceRequestNotFound {
        return ResponseEntity.ok(invoiceProcessorService.retrieveInvoiceRequest(invoiceHash));
    }

    @Override
    @GetMapping(value = INVOICE_HASH_TRANSACTION, produces = { APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Transaction>> transactionByInvoiceHash(String invoiceHash) throws InvoiceRequestNotFound {
        return ResponseEntity.ok(invoiceProcessorService.retrieveInvoiceTransactions(invoiceHash));
    }

    @Override
    @PatchMapping(value = INVOICE_HASH, produces = { APPLICATION_JSON_VALUE })
    public ResponseEntity<InvoiceRequest> updateInvoiceByHash(@PathVariable("invoiceHash") String invoiceHash, @Valid @RequestBody InvoiceRequest body) throws InvoiceRequestNotFound {
        return ResponseEntity.ok(invoiceProcessorService.updateInvoiceRequest(invoiceHash, body));
    }

    @Override
    @PostMapping(value = INVOICE_HASH_CONFIRMATION, consumes = { APPLICATION_JSON_VALUE })
    public ResponseEntity invoiceConfirmation(@PathVariable("invoiceHash") String invoiceHash, @Valid @RequestBody InvoiceConfirmationRequest body) throws InvoiceRequestNotFound {
        invoiceProcessorService.invoiceConfirmation(invoiceHash, body);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping(value = INVOICE_HASH_CANCELLATION)
    public ResponseEntity invoiceCancellation(@PathVariable("invoiceHash") String invoiceHash) throws InvoiceRequestNotFound {
        invoiceProcessorService.invoiceCancellation(invoiceHash);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping(value = INVOICE,  produces = { APPLICATION_JSON_VALUE })
    public ResponseEntity<InvoiceRequest> getAllInvoice(@Valid @RequestParam(value = "accountId", required = false) String accountId, @Valid @RequestParam(value = "invoiceType", required = false) InvoiceType invoiceType) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
    @Override
    @GetMapping(value = INVOICE_FIND_BY_USER_ADDRESS,  produces = { APPLICATION_JSON_VALUE })
    public ResponseEntity<List<InvoiceRequest>> getAllInvoiceGivenUserAddressAndFilters(@NotNull @Valid @RequestParam(value = "address") String address, @Valid @RequestParam(value = "invoiceType", required = false) InvoiceType invoiceType) {
        return ResponseEntity.ok(invoiceProcessorService.retrieveByUserAddress(address));
    }

}
