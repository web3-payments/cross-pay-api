package com.cross.chain.payment.service.payment;

import com.cross.chain.payment.domain.PaymentRequestDetails;
import com.cross.chain.payment.domain.PaymentType;
import com.cross.chain.payment.domain.ProductsPayment;
import com.cross.chain.payment.dto.*;
import com.cross.chain.payment.exception.PaymentProcessorException;
import com.cross.chain.payment.exception.PaymentRequestNotFound;
import com.cross.chain.payment.mapper.PaymentRequestMapper;
import com.cross.chain.payment.mapper.TransactionMapper;
import com.cross.chain.payment.repository.PaymentRequestRepository;
import com.cross.chain.payment.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentProcessorImpl implements PaymentProcessor {

    private final List<PaymentService> paymentServices;
    private final ProductService productService;
    private final PaymentRequestMapper mapper;

    private final TransactionMapper transactionMapper;
    private final PaymentRequestRepository repository;

    @Override
    public PaymentResponse processPaymentRequest(PaymentRequest paymentRequest) {
        PaymentService paymentService = getPaymentService(paymentRequest.getPaymentType());
        return paymentService.create(paymentRequest);
    }

    @Override
    public PaymentRequest retrievePaymentRequest(String paymentHash) throws PaymentRequestNotFound {
        PaymentRequestDetails paymentRequestDetails = repository.findByHash(paymentHash).orElseThrow(PaymentRequestNotFound::new);
        return mapper.map(paymentRequestDetails);
    }

    @Override
    public List<TransactionDTO> retrievePaymentTransactions(String paymentHash) throws PaymentRequestNotFound {
        PaymentRequestDetails paymentRequestDetails = repository.findByHash(paymentHash).orElseThrow(PaymentRequestNotFound::new);
        if(PaymentType.PAYMENT_LINK.equals(paymentRequestDetails.getPaymentType())){
            return paymentRequestDetails.getTransactions().stream()
                    .map(transactionMapper::map)
                    .collect(Collectors.toList());
        }
        throw new RuntimeException(); //TODO: should return an correct exception - Take a look in the ways in done in MCS
    }

    @Override
    public PaymentRequest updatePaymentRequest(String paymentHash, PaymentRequest paymentRequest) throws PaymentRequestNotFound {
        PaymentRequestDetails paymentRequestDetails = repository.findByHash(paymentHash).orElseThrow(PaymentRequestNotFound::new);
        PaymentRequestDetails paymentUpdated = mapper.map(paymentRequest);
//        paymentRequestDetails.setCustomerInfo(paymentUpdated.getCustomerInfo());
        paymentRequestDetails.setAmount(paymentUpdated.getAmount());
        paymentRequestDetails.setProducts(paymentUpdated.getProducts());
        updateProductDetails(paymentRequestDetails.getProducts());//TODO: check if I remove it from here mongodb will automatically handle the update of the product
        repository.save(paymentRequestDetails);
        return mapper.map(paymentRequestDetails);
    }

    private void updateProductDetails(List<ProductsPayment> products) {
        products.forEach(item-> productService.update(item.getProduct()));
    }

    @Override
    public List<PaymentRequest> retrieveByUserAddress(String address) {
        return repository.findByUserAddress(address)
                .stream().map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void paymentConfirmation(String paymentHash, PaymentConfirmationDTO paymentConfirmationDTO) throws PaymentRequestNotFound {
        PaymentRequestDetails paymentRequestDetails = repository.findByHash(paymentHash).orElseThrow(PaymentRequestNotFound::new);
        PaymentService paymentService = getPaymentService(paymentRequestDetails.getPaymentType());
        paymentService.confirm(paymentRequestDetails, paymentConfirmationDTO);
    }

    @Override
    public void paymentCancellation(String paymentHash) throws PaymentRequestNotFound {
        PaymentRequestDetails paymentRequestDetails = repository.findByHash(paymentHash).orElseThrow(PaymentRequestNotFound::new);
        PaymentService paymentService = getPaymentService(paymentRequestDetails.getPaymentType());
        paymentService.cancel(paymentRequestDetails);
    }

    private PaymentService getPaymentService(PaymentType paymentType) {
        PaymentService paymentService = paymentServices.stream()
                .filter(p -> p.applies(paymentType))
                .findFirst()
                .orElseThrow(PaymentProcessorException::new);
        return paymentService;
    }
}
