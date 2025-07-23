package com.hong.springauthapp.user.controller;

import com.hong.springauthapp.common.HttpResponse;
import com.hong.springauthapp.user.UserService;
import com.hong.springauthapp.user.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.hong.springauthapp.common.ResponseEnum.*;
import static com.hong.springauthapp.common.ResponseUtils.of;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<HttpResponse> signup(@RequestBody SignupRequest request) {
        userService.signup(request);
        return of(USER_SIGNUP_SUCCESS);
    }

    @GetMapping("/user-role")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<HttpResponse> userResponseTest() {
        return of(USER_AUTHORITY_TEST);
    }

    @GetMapping("/admin-role")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<HttpResponse> adminResponseTest() {
        return of(ADMIN_AUTHORITY_TEST);
    }

}
