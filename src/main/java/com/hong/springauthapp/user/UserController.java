package com.hong.springauthapp.user;

import com.hong.springauthapp.common.HttpResponseDto;
import com.hong.springauthapp.user.dto.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.hong.springauthapp.common.ResponseEnum.USER_SIGNUP_SUCCESS;
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

}
