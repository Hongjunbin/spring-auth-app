package com.hong.springauthapp.user.service;

import com.hong.springauthapp.redis.RedisService;
import com.hong.springauthapp.user.dto.VerificationRequest;
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
        redisService.createEmailVerificationCode(email);
        String code = redisService.getEmailVerificationCode(email);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("이메일 인증 코드");
        message.setText(code);
        javaMailSender.send(message);
    }

    public void verifyEmailCode(VerificationRequest request) {
        redisService.verifyEmailCode(request.email(), request.code());
    }

}
