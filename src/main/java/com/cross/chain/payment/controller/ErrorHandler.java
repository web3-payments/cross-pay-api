package com.cross.chain.payment.controller;

import com.cross.chain.payment.model.ErrorModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorModel> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(ErrorModel.builder().status(HttpStatus.BAD_REQUEST.value()).error(HttpStatus.BAD_REQUEST.name()).error("Validation error").build(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<ErrorModel> handleHttpMessageNotReadableException() {
//        return new ResponseEntity<>(ErrorModel.builder().status(HttpStatus.BAD_REQUEST.value()).error(HttpStatus.BAD_REQUEST.name()).error("Validation error").build(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorModel>  processValidationError(IllegalArgumentException e) {
        log.info("Returning HTTP 400 Bad Request", e);
        return new ResponseEntity<>(ErrorModel.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .error(HttpStatus.BAD_REQUEST.name())
                    .message(e.getMessage())
                .build(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
