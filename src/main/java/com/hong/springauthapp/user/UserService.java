package com.hong.springauthapp.user;

import com.hong.springauthapp.exception.CustomException;
import com.hong.springauthapp.exception.ErrorCode;
import com.hong.springauthapp.redis.RedisService;
import com.hong.springauthapp.user.dto.SignupRequest;
import com.hong.springauthapp.user.entity.Role;
import com.hong.springauthapp.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;

    public void signup(SignupRequest request) {

        userRepository.findByEmail(request.email()).ifPresent(user -> {
            throw new CustomException(ErrorCode.USER_ALREADY_EXISTS);
        });

        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .name(request.name())
                .role(Role.ROLE_USER)
                .build();

        redisService.hasVerifiedEmail(user.getEmail());

        userRepository.save(user);
    }

}
