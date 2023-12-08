package com.tskr.payment.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PaymentServiceCustomException extends RuntimeException{

    private final String errorCode;

    public PaymentServiceCustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}


