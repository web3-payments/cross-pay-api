package com.cross.chain.payment.service.payment;

import com.cross.chain.payment.domain.PaymentRequestDetails;
import com.cross.chain.payment.domain.PaymentStatus;
import com.cross.chain.payment.domain.ProductsPayment;
import com.cross.chain.payment.mapper.PaymentRequestMapper;
import com.cross.chain.payment.dto.PaymentRequest;
import com.cross.chain.payment.dto.PaymentResponse;
import com.cross.chain.payment.domain.PaymentType;
import com.cross.chain.payment.repository.PaymentRequestRepository;
import com.cross.chain.payment.service.product.ProductService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class PaymentLinkRequestServiceImpl implements PaymentRequestService {

    @Value("${payment.hash-length}")
    private int hashLength;

    @Value("${payment.url}")
    private String url;

    @Autowired
    private PaymentRequestMapper mapper;

    @Autowired
    private PaymentRequestRepository repository;

    @Autowired
    private ProductService productService;

    @Override
    public PaymentResponse createPaymentRequest(PaymentRequest paymentRequest) {
        validatePayment(paymentRequest);
        PaymentRequestDetails paymentRequestDetails = create(paymentRequest);
        return PaymentResponse.builder()
                .paymentLink(paymentRequestDetails.getPaymentLink())
                .build();
    }

    private PaymentRequestDetails create(PaymentRequest paymentRequest){
        PaymentRequestDetails paymentRequestDetails = mapper.map(paymentRequest);
        updateProductDetails(paymentRequestDetails.getProducts());
        paymentRequestDetails.setHash(RandomStringUtils.randomAlphabetic(hashLength));
        paymentRequestDetails.setPaymentLink(url.concat("/").concat(paymentRequestDetails.getHash()));
        paymentRequestDetails.setPaymentStatus(PaymentStatus.CREATED);
        return repository.save(paymentRequestDetails);
    }

    private void updateProductDetails(List<ProductsPayment> products) {
        products.forEach(item-> productService.update(item.getProduct()));
    }

    @Override
    public void validatePayment(PaymentRequest paymentRequest) {
        //TODO: validate if contains all the required information to create the link
        Assert.notNull(paymentRequest.getAmount(), String.format("Amount is required for Payment type: %s", PaymentType.PAYMENT_LINK.name()));
    }

    @Override
    public PaymentType typePayment() {
        return PaymentType.PAYMENT_LINK;
    }

}
