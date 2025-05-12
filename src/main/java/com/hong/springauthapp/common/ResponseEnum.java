package com.hong.springauthapp.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseEnum {

    USER_SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입 성공");

    private final HttpStatus httpStatus;
    private final String message;
}
