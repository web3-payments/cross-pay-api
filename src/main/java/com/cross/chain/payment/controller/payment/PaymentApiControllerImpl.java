package com.cross.chain.payment.controller.payment;

import com.cross.chain.payment.dto.PaymentConfirmationDTO;
import com.cross.chain.payment.dto.PaymentRequest;
import com.cross.chain.payment.dto.PaymentResponse;
import com.cross.chain.payment.domain.PaymentType;
import com.cross.chain.payment.exception.PaymentRequestNotFound;
import com.cross.chain.payment.service.payment.PaymentProcessor;
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
    private PaymentProcessor paymentProcessor;
    @Override
    @PostMapping(value = PAYMENT, produces = { APPLICATION_JSON_VALUE }, consumes = { APPLICATION_JSON_VALUE })
    public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody PaymentRequest body) {
        return ResponseEntity.ok(paymentProcessor.processPaymentRequest(body));
    }

    @Override
    @GetMapping(value = PAYMENT_HASH, produces = { APPLICATION_JSON_VALUE })
    public ResponseEntity<PaymentRequest> paymentByHash(@PathVariable("paymentHash") String paymentHash) throws PaymentRequestNotFound {
        return ResponseEntity.ok(paymentProcessor.retrievePaymentRequest(paymentHash));
    }

    @Override
    @PatchMapping(value = PAYMENT_HASH, produces = { APPLICATION_JSON_VALUE })
    public ResponseEntity<PaymentRequest> updatePaymentByHash(@PathVariable("paymentHash") String paymentHash, @Valid @RequestBody PaymentRequest body) throws PaymentRequestNotFound {
        return ResponseEntity.ok(paymentProcessor.updatePaymentRequest(paymentHash, body));
    }

    @Override
    @PostMapping(value = PAYMENT_HASH_CONFIRMATION, consumes = { APPLICATION_JSON_VALUE })
    public ResponseEntity paymentConfirmation(@PathVariable("paymentHash") String paymentHash, @Valid @RequestBody PaymentConfirmationDTO body) throws PaymentRequestNotFound {
        paymentProcessor.paymentConfirmation(paymentHash, body);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping(value = PAYMENT_HASH_CANCELLATION)
    public ResponseEntity paymentCancellation(@PathVariable("paymentHash") String paymentHash) throws PaymentRequestNotFound {
        paymentProcessor.paymentCancellation(paymentHash);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping(value = PAYMENT, produces = { "application/json" })
    public ResponseEntity<PaymentRequest> getAllPayment(@Valid @RequestParam(value = "accountId", required = false) String accountId, @Valid @RequestParam(value = "paymentType", required = false) PaymentType paymentType) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
    @Override
    @RequestMapping(value = PAYMENT_FIND_BY_USER_ADDRESS, produces = { "application/json" }, method = RequestMethod.GET)
    public ResponseEntity<List<PaymentRequest>> getAllPaymentGivenUserAddressAndFilters(@NotNull @Valid @RequestParam(value = "address") String address, @Valid @RequestParam(value = "paymentType", required = false) PaymentType paymentType) {
        return ResponseEntity.ok(paymentProcessor.retrieveByUserAddress(address));
    }

}
