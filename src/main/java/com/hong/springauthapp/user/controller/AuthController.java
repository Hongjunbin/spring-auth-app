package com.hong.springauthapp.user.controller;

import com.hong.springauthapp.common.HttpResponse;
import com.hong.springauthapp.common.ResponseEnum;
import com.hong.springauthapp.user.dto.EmailRequest;
import com.hong.springauthapp.user.dto.VerificationRequest;
import com.hong.springauthapp.user.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.hong.springauthapp.common.ResponseUtils.of;

@Tag(name = "인증 API", description = "이메일 전송 및 인증 추후의 다른 인증 추가예정")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/email/send")
    @Operation(summary = "이메일 인증 코드 발급 및 이메일 전송")
    public ResponseEntity<HttpResponse> sendVerificationEmail(@RequestBody EmailRequest request) {
        authService.sendVerificationEmail(request.email());
        return of(ResponseEnum.USER_SIGNUP_SUCCESS);
    }

    @PostMapping("/email/verify")
    @Operation(summary = "이메일 인증 코드 검증 및 인증 완료 상태 저장")
    public ResponseEntity<HttpResponse> verifyEmailCode(@RequestBody VerificationRequest request) {
        authService.verifyEmailCode(request);
        return of(ResponseEnum.USER_SIGNUP_SUCCESS);
    }

}
