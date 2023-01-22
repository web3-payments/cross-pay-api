package com.cross.chain.payment.persistence;

import com.cross.chain.payment.exception.PaymentRequestNotFound;
import com.cross.chain.payment.model.PaymentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentRequestDbServiceImpl implements PaymentRequestDbService {

    private final PaymentRequestRepository repository;
    private final PaymentRequestDbMapper mapper;
    @Override
    public PaymentRequestDTO findByHash(String paymentHash) throws PaymentRequestNotFound {
        return mapper.map(repository.findByHash(paymentHash).orElseThrow(PaymentRequestNotFound::new));
    }

    @Override
    public PaymentRequestDTO save(PaymentRequestDTO paymentRequest) {
        PaymentRequestEntity paymentRequestEntity = mapper.map(paymentRequest);
        return mapper.map(repository.save(paymentRequestEntity));
    }

    @Override
    public List<PaymentRequestDTO> findByUserAddress(String address) {
        return repository.findByUserAddress(address).stream().map(mapper::map).collect(Collectors.toList());
    }
}