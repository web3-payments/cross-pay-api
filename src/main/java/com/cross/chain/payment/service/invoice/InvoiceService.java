package com.cross.chain.payment.service.invoice;

import com.cross.chain.payment.model.*;
import com.cross.chain.payment.model.enums.InvoiceType;

public interface InvoiceService {

    InvoiceResponse create(InvoiceRequest invoiceRequest);

    InvoiceRequestDTO confirm(InvoiceRequestDTO invoiceRequest, InvoiceConfirmationDTO invoiceConfirmationDTO);

    InvoiceRequestDTO cancel(InvoiceRequestDTO invoiceRequest);

    void validateInvoice(InvoiceRequest invoiceRequest);
    default boolean applies(InvoiceType type){
        return typeInvoice().equals(type);
    }
    InvoiceType typeInvoice();

}
