package com.cross.chain.payment.controller.payment;

import com.cross.chain.payment.model.PaymentRequest;
import com.cross.chain.payment.model.PaymentResponse;
import com.cross.chain.payment.model.PaymentType;
import com.cross.chain.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class PaymentApiControllerImpl implements PaymentApiController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/payment", produces = { "application/json" }, consumes = { "application/json" })
    public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody PaymentRequest body) {
        //TODO: check if the payment response is equal to the swager documentation
        return ResponseEntity.ok(paymentService.processPaymentRequest(body));
    }

    @GetMapping(value = "/payment/{paymentHash}", produces = { "application/json" })
    public ResponseEntity<PaymentRequest> paymentByHash(@PathVariable("paymentHash") String paymentHash) {
        return ResponseEntity.ok(paymentService.retrievePaymentRequest(paymentHash));
    }

    @GetMapping(value = "/payment", produces = { "application/json" })
    public ResponseEntity<PaymentRequest> getAllPayment(@Valid @RequestParam(value = "accountId", required = false) String accountId, @Valid @RequestParam(value = "paymentType", required = false) PaymentType paymentType) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(value = "/payment/findByAccountId", produces = { "application/json" }, method = RequestMethod.GET)
    public ResponseEntity<PaymentRequest> getAllPaymentGivenAccountAndFilters(@NotNull @Valid @RequestParam(value = "accountId") String accountId, @Valid @RequestParam(value = "paymentType", required = false) PaymentType paymentType) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
