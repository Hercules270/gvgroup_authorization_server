package com.gvgroup.usermanagement.exception;

import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends BaseException{

    public UserAlreadyExistsException(String message, String responseMessage, ErrorCode errorCode) {
        super(message, responseMessage, errorCode);
    }

}
