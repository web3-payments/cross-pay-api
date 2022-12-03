package com.cross.chain.payment.service.payment;

import com.cross.chain.payment.domain.PaymentRequestDetails;
import com.cross.chain.payment.domain.ProductsPayment;
import com.cross.chain.payment.dto.PaymentConfirmationDTO;
import com.cross.chain.payment.exception.PaymentProcessorException;
import com.cross.chain.payment.exception.PaymentRequestNotFound;
import com.cross.chain.payment.mapper.PaymentRequestMapper;
import com.cross.chain.payment.dto.PaymentRequest;
import com.cross.chain.payment.dto.PaymentResponse;
import com.cross.chain.payment.repository.PaymentRequestRepository;
import com.cross.chain.payment.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentProcessorImpl implements PaymentProcessor {

    @Autowired
    List<PaymentService> paymentServices;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentRequestMapper mapper;

    @Autowired
    private PaymentRequestRepository repository;

    @Override
    public PaymentResponse processPaymentRequest(PaymentRequest paymentRequest) {
        PaymentService paymentService = paymentServices.stream()
                .filter(p -> p.applies(paymentRequest.getPaymentType()))
                .findFirst()
                .orElseThrow(PaymentProcessorException::new);
        return paymentService.create(paymentRequest);
    }

    @Override
    public PaymentRequest retrievePaymentRequest(String paymentHash) throws PaymentRequestNotFound {
        PaymentRequestDetails paymentRequestDetails = repository.findByHash(paymentHash).orElseThrow(PaymentRequestNotFound::new);
        return mapper.map(paymentRequestDetails);
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
        PaymentService paymentService = paymentServices.stream()
                .filter(p -> p.applies(paymentRequestDetails.getPaymentType()))
                .findFirst()
                .orElseThrow(PaymentProcessorException::new);
        paymentService.confirm(paymentRequestDetails, paymentConfirmationDTO);
    }

    @Override
    public void paymentCancellation(String paymentHash) throws PaymentRequestNotFound {
        PaymentRequestDetails paymentRequestDetails = repository.findByHash(paymentHash).orElseThrow(PaymentRequestNotFound::new);
        PaymentService paymentService = paymentServices.stream()
                .filter(p -> p.applies(paymentRequestDetails.getPaymentType()))
                .findFirst()
                .orElseThrow(PaymentProcessorException::new);
        paymentService.cancel(paymentRequestDetails);
    }
}
