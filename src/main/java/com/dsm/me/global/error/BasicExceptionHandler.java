package com.dsm.me.global.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class BasicExceptionHandler {
    @ExceptionHandler
    protected ResponseEntity<ExceptionResponse> basicExceptionHandler(final BasicException e) {
        final ErrorCode message = e.getErrorCode();
        return new ResponseEntity<>(new ExceptionResponse(message.getCode(), message.getMessage()), HttpStatus.valueOf(message.getCode()));
    }
}
