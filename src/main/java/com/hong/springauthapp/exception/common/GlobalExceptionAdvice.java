package com.hong.springauthapp.exception.common;

import com.hong.springauthapp.common.HttpResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.hong.springauthapp.common.ResponseUtils.of;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<HttpResponseDto> handleUserException(CommonException e) {
        return of(e.getResponseExceptionEnum());
    }
}
