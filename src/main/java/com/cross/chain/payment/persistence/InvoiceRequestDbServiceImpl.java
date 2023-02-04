package com.cross.chain.payment.persistence;

import com.cross.chain.payment.exception.InvoiceRequestNotFound;
import com.cross.chain.payment.model.InvoiceRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceRequestDbServiceImpl implements InvoiceRequestDbService {

    private final InvoiceRequestRepository repository;
    private final InvoiceRequestDbMapper mapper;
    @Override
    public InvoiceRequestDTO findByHash(String invoiceHash) throws InvoiceRequestNotFound {
        return mapper.map(repository.findByHash(invoiceHash).orElseThrow(InvoiceRequestNotFound::new));
    }

    @Override
    public InvoiceRequestDTO save(InvoiceRequestDTO invoiceRequest) {
        InvoiceRequestEntity invoiceRequestEntity = mapper.map(invoiceRequest);
        return mapper.map(repository.save(invoiceRequestEntity));
    }

    @Override
    public List<InvoiceRequestDTO> findByUserAddress(String address) {
        return repository.findByUserAddress(address).stream().map(mapper::map).collect(Collectors.toList());
    }
}