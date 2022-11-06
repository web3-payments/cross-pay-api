package com.cross.chain.payment.controller.payment;

import com.cross.chain.payment.dto.PaymentConfirmation;
import com.cross.chain.payment.dto.PaymentRequest;
import com.cross.chain.payment.dto.PaymentResponse;
import com.cross.chain.payment.domain.PaymentType;
import com.cross.chain.payment.service.payment.PaymentService;
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
    private PaymentService paymentService;
    @Override
    @PostMapping(value = "/payment", produces = { APPLICATION_JSON_VALUE }, consumes = { APPLICATION_JSON_VALUE })
    public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody PaymentRequest body) {
        return ResponseEntity.ok(paymentService.processPaymentRequest(body));
    }

    @Override
    @GetMapping(value = "/payment/{paymentHash}", produces = { APPLICATION_JSON_VALUE })
    public ResponseEntity<PaymentRequest> paymentByHash(@PathVariable("paymentHash") String paymentHash) {
        return ResponseEntity.ok(paymentService.retrievePaymentRequest(paymentHash));
    }

    @Override
    @PostMapping(value = "/payment/{paymentHash}/confirmation", consumes = { APPLICATION_JSON_VALUE })
    public ResponseEntity paymentConfirmation(@PathVariable("paymentHash") String paymentHash, @Valid @RequestBody PaymentConfirmation body) {
        paymentService.paymentConfirmation(paymentHash, body);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping(value = "/payment/{paymentHash}/cancellation")
    public ResponseEntity paymentCancellation(@PathVariable("paymentHash") String paymentHash) {
        paymentService.paymentCancellation(paymentHash);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping(value = "/payment", produces = { "application/json" })
    public ResponseEntity<PaymentRequest> getAllPayment(@Valid @RequestParam(value = "accountId", required = false) String accountId, @Valid @RequestParam(value = "paymentType", required = false) PaymentType paymentType) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
    @Override
    @RequestMapping(value = "/payment/findByUserAddress", produces = { "application/json" }, method = RequestMethod.GET)
    public ResponseEntity<List<PaymentRequest>> getAllPaymentGivenUserAddressAndFilters(@NotNull @Valid @RequestParam(value = "address") String address, @Valid @RequestParam(value = "paymentType", required = false) PaymentType paymentType) {
        return ResponseEntity.ok(paymentService.retrieveByUserAddress(address));
    }

}
