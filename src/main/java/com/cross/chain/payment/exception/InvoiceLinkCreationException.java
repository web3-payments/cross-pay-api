package com.cross.chain.payment.exception;

public class InvoiceLinkCreationException extends RuntimeException {
        public InvoiceLinkCreationException(Exception exception){
        super(exception);
    }

}
