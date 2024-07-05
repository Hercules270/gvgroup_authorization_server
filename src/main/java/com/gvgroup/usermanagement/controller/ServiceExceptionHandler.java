package com.gvgroup.usermanagement.controller;


import com.gvgroup.usermanagement.exception.UserAlreadyExistsException;
import com.gvgroup.usermanagement.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.gvgroup.usermanagement.exception.ErrorCode.GENERAL_ERROR;


@Slf4j
@RestControllerAdvice
public class ServiceExceptionHandler {


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> userAlreadyExistsExceptionHandler(UserAlreadyExistsException ex) {
        log.error("User already exists exception", ex);
        return new ResponseEntity<>(new ErrorResponse(ex.getResponseMessage(), ex.getErrorCode().getCode()), ex.getErrorCode().getHttpStatus());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> userAlreadyExistsExceptionHandler(Exception ex) {
        log.error("Unhandled exception happened", ex);
        return new ResponseEntity<>(new ErrorResponse("General Error", GENERAL_ERROR.getCode()), HttpStatus.BAD_REQUEST);
    }

}
