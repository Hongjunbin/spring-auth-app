package com.hong.springauthapp.exception;

import com.hong.springauthapp.common.HttpResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.hong.springauthapp.common.ResponseUtils.of;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<HttpResponseDto> handleUserException(CommonException e) {
        return of(e.getResponseExceptionEnum());
    }
}
