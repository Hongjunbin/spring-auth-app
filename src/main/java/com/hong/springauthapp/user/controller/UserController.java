package com.hong.springauthapp.user.controller;

import com.hong.springauthapp.common.HttpResponseDto;
import com.hong.springauthapp.user.UserService;
import com.hong.springauthapp.user.dto.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<HttpResponseDto> signup(@RequestBody SignupRequestDto requestDto) {
        userService.signup(requestDto);
        return of(USER_SIGNUP_SUCCESS);
    }

    @GetMapping("/user-role")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> userResponseTest() {
        return ResponseEntity.status(HttpStatus.OK).body("유저 권한 테스트");
    }

    @GetMapping("/admin-role")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> adminResponseTest() {
        return ResponseEntity.status(HttpStatus.OK).body("어드민 권한 테스트");
    }

}
