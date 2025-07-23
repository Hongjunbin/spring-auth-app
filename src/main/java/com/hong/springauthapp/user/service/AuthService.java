package com.hong.springauthapp.user.service;

import com.hong.springauthapp.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RedisService redisService;
    private final JavaMailSender javaMailSender;

    public void sendVerificationEmail(String email) {
        redisService.createVerificationCode(email);
        String code = redisService.getVerificationCode(email);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("이메일 인증 코드");
        message.setText(code);
        javaMailSender.send(message);
    }

}
