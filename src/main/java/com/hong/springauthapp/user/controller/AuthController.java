package com.hong.springauthapp.user.controller;

import com.hong.springauthapp.common.HttpResponse;
import com.hong.springauthapp.common.ResponseEnum;
import com.hong.springauthapp.user.dto.EmailRequest;
import com.hong.springauthapp.user.dto.VerificationRequest;
import com.hong.springauthapp.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.hong.springauthapp.common.ResponseUtils.of;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/email/send")
    public ResponseEntity<HttpResponse> sendVerificationEmail(@RequestBody EmailRequest request) {
        authService.sendVerificationEmail(request.email());
        return of(ResponseEnum.USER_SIGNUP_SUCCESS);
    }

    @PostMapping("/email/verify")
    public ResponseEntity<HttpResponse> verifyEmailCode(@RequestBody VerificationRequest request) {
        authService.verifyEmailCode(request);
        return of(ResponseEnum.USER_SIGNUP_SUCCESS);
    }

}
