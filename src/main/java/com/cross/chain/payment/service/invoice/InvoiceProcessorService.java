package com.cross.chain.payment.service.invoice;

import com.cross.chain.payment.exception.InvoiceRequestNotFound;
import com.cross.chain.payment.model.InvoiceConfirmationRequest;
import com.cross.chain.payment.model.InvoiceRequest;
import com.cross.chain.payment.model.InvoiceResponse;
import com.cross.chain.payment.model.Transaction;

import java.util.List;

public interface InvoiceProcessorService {

    InvoiceResponse processInvoiceRequest(InvoiceRequest invoiceRequest);

    InvoiceRequest retrieveInvoiceRequest(String invoiceHash) throws InvoiceRequestNotFound;

    List<Transaction> retrieveInvoiceTransactions(String invoiceHash) throws InvoiceRequestNotFound;

    InvoiceRequest updateInvoiceRequest(String invoiceHash, InvoiceRequest invoiceRequest) throws InvoiceRequestNotFound;

    List<InvoiceRequest> retrieveByUserAddress(String address);

    void invoiceConfirmation(String invoiceHash, InvoiceConfirmationRequest invoiceConfirmation) throws InvoiceRequestNotFound;

    void invoiceCancellation(String invoiceHash) throws InvoiceRequestNotFound;
}
