package com.hong.springauthapp.user.controller;

import com.hong.springauthapp.common.HttpResponseDto;
import com.hong.springauthapp.common.ResponseEnum;
import com.hong.springauthapp.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.hong.springauthapp.common.ResponseUtils.of;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/email/send")
    public ResponseEntity<HttpResponseDto> sendVerificationEmail(@RequestParam String email) {
        authService.sendVerificationEmail(email);
        return of(ResponseEnum.USER_SIGNUP_SUCCESS);
    }

}
