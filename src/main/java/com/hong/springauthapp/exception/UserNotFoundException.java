package com.hong.springauthapp.exception;

import com.hong.springauthapp.exception.common.CommonException;
import com.hong.springauthapp.exception.common.ResponseExceptionEnum;

public class UserNotFoundException extends CommonException {
    public UserNotFoundException(ResponseExceptionEnum responseExceptionEnum) {
        super(responseExceptionEnum);
    }
}
