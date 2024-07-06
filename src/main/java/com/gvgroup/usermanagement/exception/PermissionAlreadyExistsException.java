package com.gvgroup.usermanagement.exception;

public class PermissionAlreadyExistsException extends BaseException {

    public PermissionAlreadyExistsException(String message, String responseMessage, ErrorCode errorCode) {
        super(message, responseMessage, errorCode);
    }
}
