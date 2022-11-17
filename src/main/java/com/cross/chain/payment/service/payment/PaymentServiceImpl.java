package com.cross.chain.payment.service.payment;

import com.cross.chain.payment.domain.PaymentRequestDetails;
import com.cross.chain.payment.domain.PaymentStatus;
import com.cross.chain.payment.domain.ProductsPayment;
import com.cross.chain.payment.dto.PaymentConfirmation;
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
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    List<PaymentRequestService> paymentRequestServices;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentRequestMapper mapper;

    @Autowired
    private PaymentRequestRepository repository;

    @Override
    public PaymentResponse processPaymentRequest(PaymentRequest paymentRequest) {
        PaymentRequestService paymentRequestService = paymentRequestServices.stream()
                .filter(p -> p.applies(paymentRequest.getPaymentType()))
                .findFirst()
                .orElseThrow(RuntimeException::new);//TODO: change exception
        return paymentRequestService.createPaymentRequest(paymentRequest);
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
        paymentRequestDetails.setCustomerInfo(paymentUpdated.getCustomerInfo());
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
    public void paymentConfirmation(String paymentHash, PaymentConfirmation paymentConfirmation) throws PaymentRequestNotFound {
        PaymentRequestDetails paymentRequestDetails = repository.findByHash(paymentHash).orElseThrow(PaymentRequestNotFound::new);
        //TODO: create a transaction history using the paymentConfirmation
        paymentRequestDetails.setPaymentStatus(PaymentStatus.PAID);
        repository.save(paymentRequestDetails);
    }

    @Override
    public void paymentCancellation(String paymentHash) throws PaymentRequestNotFound {
        PaymentRequestDetails paymentRequestDetails = repository.findByHash(paymentHash).orElseThrow(PaymentRequestNotFound::new); //TODO: change exception
        //TODO: create a transaction history using the paymentConfirmation
        if(paymentRequestDetails.getPaymentStatus().isFinalStatus()){
            throw new RuntimeException();
        }
        //TODO: increate the total supply since the payment was cancelled.
        paymentRequestDetails.setPaymentStatus(PaymentStatus.CANCELLED);
        repository.save(paymentRequestDetails);
    }
}
