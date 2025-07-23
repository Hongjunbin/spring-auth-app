package com.hong.springauthapp.exception;

import com.hong.springauthapp.common.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.hong.springauthapp.common.ResponseUtils.of;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<HttpResponse> handleUserException(CustomException e) {
        return of(e.getErrorCode());
    }
}
