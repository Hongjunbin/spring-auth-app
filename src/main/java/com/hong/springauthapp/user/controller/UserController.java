package com.hong.springauthapp.user.controller;

import com.hong.springauthapp.common.HttpResponse;
import com.hong.springauthapp.user.UserService;
import com.hong.springauthapp.user.dto.SignupRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.hong.springauthapp.common.ResponseEnum.*;
import static com.hong.springauthapp.common.ResponseUtils.of;

@Tag(name = "유저 API", description = "회원가입, 권한 테스트")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @PostMapping("/signup")
    @Operation(summary = "회원가입: 이메일 인증 후 인증 완료 상태가 있어야 가능")
    public ResponseEntity<HttpResponse> signup(@RequestBody SignupRequest request) {
        userService.signup(request);
        return of(USER_SIGNUP_SUCCESS);
    }

    @GetMapping("/user-role")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(summary = "일반 유저 권한 테스트")
    public ResponseEntity<HttpResponse> userResponseTest() {
        return of(USER_AUTHORITY_TEST);
    }

    @GetMapping("/admin-role")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "어드민 유저 권한 테스트")
    public ResponseEntity<HttpResponse> adminResponseTest() {
        return of(ADMIN_AUTHORITY_TEST);
    }

}
