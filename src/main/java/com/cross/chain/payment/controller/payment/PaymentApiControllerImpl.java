package com.cross.chain.payment.controller.payment;

import com.cross.chain.payment.model.PaymentConfirmationRequest;
import com.cross.chain.payment.model.PaymentRequest;
import com.cross.chain.payment.model.PaymentResponse;
import com.cross.chain.payment.model.Transaction;
import com.cross.chain.payment.model.enums.PaymentType;
import com.cross.chain.payment.exception.PaymentRequestNotFound;
import com.cross.chain.payment.service.payment.PaymentProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;

import static org.springframework.http.MediaType.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class PaymentApiControllerImpl implements PaymentApiController {

    @Autowired
    private PaymentProcessorService paymentProcessorService;
    @Override
    @PostMapping(value = PAYMENT, produces = { APPLICATION_JSON_VALUE }, consumes = { APPLICATION_JSON_VALUE })
    public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody PaymentRequest body) {
        return ResponseEntity.ok(paymentProcessorService.processPaymentRequest(body));
    }

    @Override
    @GetMapping(value = PAYMENT_HASH, produces = { APPLICATION_JSON_VALUE })
    public ResponseEntity<PaymentRequest> paymentByHash(@PathVariable("paymentHash") String paymentHash) throws PaymentRequestNotFound {
        return ResponseEntity.ok(paymentProcessorService.retrievePaymentRequest(paymentHash));
    }

    @Override
    @GetMapping(value = PAYMENT_HASH_TRANSACTION, produces = { APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Transaction>> transactionByPaymentHash(String paymentHash) throws PaymentRequestNotFound {
        return ResponseEntity.ok(paymentProcessorService.retrievePaymentTransactions(paymentHash));
    }

    @Override
    @PatchMapping(value = PAYMENT_HASH, produces = { APPLICATION_JSON_VALUE })
    public ResponseEntity<PaymentRequest> updatePaymentByHash(@PathVariable("paymentHash") String paymentHash, @Valid @RequestBody PaymentRequest body) throws PaymentRequestNotFound {
        return ResponseEntity.ok(paymentProcessorService.updatePaymentRequest(paymentHash, body));
    }

    @Override
    @PostMapping(value = PAYMENT_HASH_CONFIRMATION, consumes = { APPLICATION_JSON_VALUE })
    public ResponseEntity paymentConfirmation(@PathVariable("paymentHash") String paymentHash, @Valid @RequestBody PaymentConfirmationRequest body) throws PaymentRequestNotFound {
        paymentProcessorService.paymentConfirmation(paymentHash, body);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping(value = PAYMENT_HASH_CANCELLATION)
    public ResponseEntity paymentCancellation(@PathVariable("paymentHash") String paymentHash) throws PaymentRequestNotFound {
        paymentProcessorService.paymentCancellation(paymentHash);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping(value = PAYMENT,  produces = { APPLICATION_JSON_VALUE })
    public ResponseEntity<PaymentRequest> getAllPayment(@Valid @RequestParam(value = "accountId", required = false) String accountId, @Valid @RequestParam(value = "paymentType", required = false) PaymentType paymentType) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
    @Override
    @GetMapping(value = PAYMENT_FIND_BY_USER_ADDRESS,  produces = { APPLICATION_JSON_VALUE })
    public ResponseEntity<List<PaymentRequest>> getAllPaymentGivenUserAddressAndFilters(@NotNull @Valid @RequestParam(value = "address") String address, @Valid @RequestParam(value = "paymentType", required = false) PaymentType paymentType) {
        return ResponseEntity.ok(paymentProcessorService.retrieveByUserAddress(address));
    }

}
