package com.hong.springauthapp.user;

import com.hong.springauthapp.redis.RedisService;
import com.hong.springauthapp.user.dto.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;

    public void signup(SignupRequestDto requestDto) {
        User user = User.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .name(requestDto.getName())
                .role(Role.ROLE_USER)
                .build();

        redisService.createVerificationCode(user.getEmail());

        userRepository.save(user);
    }

}
