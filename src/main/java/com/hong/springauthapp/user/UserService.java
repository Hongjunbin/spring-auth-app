package com.hong.springauthapp.user;

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
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .role(Role.ROLE_USER)
                .build();

        redisService.createVerificationCode(user.getEmail());

        userRepository.save(user);
    }

}
