package com.cross.chain.payment.service.invoice;

import com.cross.chain.payment.exception.InvoiceProcessorException;
import com.cross.chain.payment.exception.InvoiceRequestNotFound;
import com.cross.chain.payment.mapper.InvoiceRequestMapper;
import com.cross.chain.payment.mapper.TransactionMapper;
import com.cross.chain.payment.model.*;
import com.cross.chain.payment.model.enums.InvoiceType;
import com.cross.chain.payment.persistence.InvoiceRequestDbService;
import com.cross.chain.payment.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceProcessorServiceImpl implements InvoiceProcessorService {

    private final List<InvoiceService> invoiceServices;
    private final ProductService productService;
    private final InvoiceRequestMapper invoiceRequestMapper;

    private final TransactionMapper transactionMapper;
    private final InvoiceRequestDbService invoiceRequestDbService;

    @Override
    public InvoiceResponse processInvoiceRequest(InvoiceRequest invoiceRequest) {
        InvoiceService invoiceService = getInvoiceService(InvoiceType.INVOICE);
        return invoiceService.create(invoiceRequest);
    }

    @Override
    public InvoiceRequest retrieveInvoiceRequest(String invoiceHash) throws InvoiceRequestNotFound {
        InvoiceRequestDTO invoiceRequest = invoiceRequestDbService.findByHash(invoiceHash);
        return invoiceRequestMapper.map(invoiceRequest);
    }

    @Override
    public List<Transaction> retrieveInvoiceTransactions(String invoiceHash) throws InvoiceRequestNotFound {
        InvoiceRequestDTO invoiceRequest = invoiceRequestDbService.findByHash(invoiceHash);
        return Collections.singletonList(transactionMapper.map(invoiceRequest.getTransaction()));
    }

    @Override
    public InvoiceRequest updateInvoiceRequest(String invoiceHash, InvoiceRequest invoiceRequest) throws InvoiceRequestNotFound {
        InvoiceRequestDTO invoiceRequestFound = invoiceRequestDbService.findByHash(invoiceHash);
        InvoiceRequestDTO invoiceUpdated = invoiceRequestMapper.map(invoiceRequest);
        invoiceRequestFound.setCreditAddress(invoiceUpdated.getCreditAddress());
        invoiceRequestFound.setMemo(invoiceUpdated.getMemo());
        invoiceRequestFound.setAmount(invoiceUpdated.getAmount());
        invoiceRequestFound.setProducts(invoiceUpdated.getProducts());
        invoiceRequestDbService.save(invoiceRequestFound);
        return invoiceRequestMapper.map(invoiceRequestFound);
    }

    @Override
    public List<InvoiceRequest> retrieveByUserAddress(String address) {
        return invoiceRequestDbService.findByUserAddress(address).stream().map(invoiceRequestMapper::map).collect(Collectors.toList());
    }

    @Override
    public void invoiceConfirmation(String invoiceHash, InvoiceConfirmationRequest invoiceConfirmationRequest) throws InvoiceRequestNotFound {
        InvoiceRequestDTO invoiceRequest = invoiceRequestDbService.findByHash(invoiceHash);
        InvoiceService invoiceService = getInvoiceService(InvoiceType.INVOICE);
        invoiceService.confirm(invoiceRequest, invoiceRequestMapper.map(invoiceConfirmationRequest));
    }

    @Override
    public void invoiceCancellation(String invoiceHash) throws InvoiceRequestNotFound {
        InvoiceRequestDTO invoiceRequest = invoiceRequestDbService.findByHash(invoiceHash);
        InvoiceService invoiceService = getInvoiceService(InvoiceType.INVOICE);
        invoiceService.cancel(invoiceRequest);
    }

    private InvoiceService getInvoiceService(InvoiceType invoiceType) {
        return invoiceServices.stream()
                .filter(p -> p.applies(invoiceType))
                .findFirst()
                .orElseThrow(InvoiceProcessorException::new);
    }
}
