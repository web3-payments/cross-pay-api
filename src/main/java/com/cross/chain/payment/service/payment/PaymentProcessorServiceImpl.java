package com.cross.chain.payment.service.payment;

import com.cross.chain.payment.model.*;
import com.cross.chain.payment.persistence.PaymentRequestDbService;
import com.cross.chain.payment.model.enums.PaymentType;
import com.cross.chain.payment.exception.PaymentProcessorException;
import com.cross.chain.payment.exception.PaymentRequestNotFound;
import com.cross.chain.payment.mapper.PaymentRequestMapper;
import com.cross.chain.payment.mapper.TransactionMapper;
import com.cross.chain.payment.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentProcessorServiceImpl implements PaymentProcessorService {

    private final List<PaymentService> paymentServices;
    private final ProductService productService;
    private final PaymentRequestMapper paymentRequestMapper;

    private final TransactionMapper transactionMapper;
    private final PaymentRequestDbService paymentRequestDbService;

    @Override
    public PaymentResponse processPaymentRequest(PaymentRequest paymentRequest) {
        PaymentService paymentService = getPaymentService(paymentRequest.getPaymentType());
        return paymentService.create(paymentRequest);
    }

    @Override
    public PaymentRequest retrievePaymentRequest(String paymentHash) throws PaymentRequestNotFound {
        PaymentRequestDTO paymentRequest = paymentRequestDbService.findByHash(paymentHash);
        return paymentRequestMapper.map(paymentRequest);
    }

    @Override
    public List<Transaction> retrievePaymentTransactions(String paymentHash) throws PaymentRequestNotFound {
        PaymentRequestDTO paymentRequest = paymentRequestDbService.findByHash(paymentHash);
        if(PaymentType.PAYMENT_LINK.equals(paymentRequest.getPaymentType())){
            return paymentRequest.getTransactions().stream()
                    .map(transactionMapper::map)
                    .collect(Collectors.toList());
        }
        throw new RuntimeException(); //TODO: should return an correct exception - Take a look in the ways in done in MCS
    }

    @Override
    public PaymentRequest updatePaymentRequest(String paymentHash, PaymentRequest paymentRequest) throws PaymentRequestNotFound {
        PaymentRequestDTO paymentRequestFound = paymentRequestDbService.findByHash(paymentHash);
        PaymentRequestDTO paymentUpdated = paymentRequestMapper.map(paymentRequest);
        paymentRequestFound.setCustomerRequiredInfo(paymentUpdated.getCustomerRequiredInfo());
        paymentRequestFound.setAdjustableQuantity(paymentUpdated.isAdjustableQuantity());
        paymentRequestFound.setCreditAddress(paymentUpdated.getCreditAddress());
        paymentRequestFound.setDescription(paymentUpdated.getDescription());
        paymentRequestFound.setAmount(paymentUpdated.getAmount());
        paymentRequestFound.setProducts(paymentUpdated.getProducts());
        paymentRequestDbService.save(paymentRequestFound);
        return paymentRequestMapper.map(paymentRequestFound);
    }

    @Override
    public List<PaymentRequest> retrieveByUserAddress(String address) {
        return paymentRequestDbService.findByUserAddress(address).stream().map(paymentRequestMapper::map).collect(Collectors.toList());
    }

    @Override
    public void paymentConfirmation(String paymentHash, PaymentConfirmationRequest paymentConfirmationRequest) throws PaymentRequestNotFound {
        PaymentRequestDTO paymentRequest = paymentRequestDbService.findByHash(paymentHash);
        PaymentService paymentService = getPaymentService(paymentRequest.getPaymentType());
        paymentService.confirm(paymentRequest, paymentRequestMapper.map(paymentConfirmationRequest));
    }

    @Override
    public void paymentCancellation(String paymentHash) throws PaymentRequestNotFound {
        PaymentRequestDTO paymentRequest = paymentRequestDbService.findByHash(paymentHash);
        PaymentService paymentService = getPaymentService(paymentRequest.getPaymentType());
        paymentService.cancel(paymentRequest);
    }

    private PaymentService getPaymentService(PaymentType paymentType) {
        return paymentServices.stream()
                .filter(p -> p.applies(paymentType))
                .findFirst()
                .orElseThrow(PaymentProcessorException::new);
    }
}
