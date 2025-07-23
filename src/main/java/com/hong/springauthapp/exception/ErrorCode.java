package com.hong.springauthapp.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "유저가 존재하지 않습니다."),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 사용자입니다.(이메일 중복)"),
    VERIFICATION_CODE_NOT_FOUND(HttpStatus.NOT_FOUND, "인증 코드가 존재하지 않거나 만료 되었습니다."),
    VERIFICATION_CODE_MIS_MATCH(HttpStatus.UNAUTHORIZED, "인증 코드가 일치하지 않습니다."),
    EMAIL_NOT_VERIFIED(HttpStatus.BAD_REQUEST, "이메일 인증이 되어있지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
