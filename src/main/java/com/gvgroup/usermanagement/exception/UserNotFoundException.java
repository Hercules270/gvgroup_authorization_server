package com.gvgroup.usermanagement.exception;



public class UserNotFoundException extends BaseException {

    public UserNotFoundException(String message, String responseMessage, ErrorCode errorCode) {
        super(message, responseMessage, errorCode);
    }
}