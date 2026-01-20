package com.hexagonal.pricing.infrastructure.adapters.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hexagonal.pricing.domain.exception.PriceNotFoundException;

@ControllerAdvice
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(PriceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlePriceNotFound(PriceNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolation(MissingServletRequestParameterException ex) {
        return ex.getMessage();
    }

}
