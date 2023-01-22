package com.cross.chain.payment.persistence;

import com.cross.chain.payment.exception.PaymentRequestNotFound;
import com.cross.chain.payment.model.PaymentRequestDTO;

import java.util.List;
import java.util.stream.DoubleStream;

public interface PaymentRequestDbService {
    PaymentRequestDTO findByHash(String paymentHash) throws PaymentRequestNotFound;

    PaymentRequestDTO save(PaymentRequestDTO paymentRequestFound);

    List<PaymentRequestDTO> findByUserAddress(String address);
}