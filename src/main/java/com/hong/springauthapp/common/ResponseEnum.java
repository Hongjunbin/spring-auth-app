package com.hong.springauthapp.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseEnum {

    USER_SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입 성공"),
    USER_AUTHORITY_TEST(HttpStatus.OK, "USER 권한 테스트 성공"),
    ADMIN_AUTHORITY_TEST(HttpStatus.OK, "ADMIN 권한 테스트 성공"),
    EMAIL_VERIFICATION_CODE_SENT(HttpStatus.OK, "이메일 인증 코드 발급 및 이메일 전송 성공"),
    EMAIL_VERIFICATION_SUCCESS(HttpStatus.OK, "이메일 인증 코드 검증 및 인증 완료 상태 저장 성공");

    private final HttpStatus httpStatus;
    private final String message;
}
