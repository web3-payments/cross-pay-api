package com.cross.chain.payment.persistence;

import com.cross.chain.payment.exception.InvoiceRequestNotFound;
import com.cross.chain.payment.model.InvoiceRequestDTO;

import java.util.List;

public interface InvoiceRequestDbService {
    InvoiceRequestDTO findByHash(String invoiceHash) throws InvoiceRequestNotFound;

    InvoiceRequestDTO save(InvoiceRequestDTO invoiceRequestFound);

    List<InvoiceRequestDTO> findByUserAddress(String address);
}