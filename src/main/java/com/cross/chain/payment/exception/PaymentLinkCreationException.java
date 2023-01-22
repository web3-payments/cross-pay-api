package com.cross.chain.payment.exception;

public class PaymentLinkCreationException extends RuntimeException {
        public PaymentLinkCreationException(Exception exception){
        super(exception);
    }

}
