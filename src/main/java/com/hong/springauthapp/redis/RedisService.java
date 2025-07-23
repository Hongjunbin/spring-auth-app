package com.hong.springauthapp.redis;

import com.hong.springauthapp.exception.CustomException;
import com.hong.springauthapp.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

import static com.hong.springauthapp.redis.RedisKey.*;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public void createEmailVerificationCode(String email) {
        String key = VERIFICATION_CODE.getKeyPrefix() + email;
        // 이미 이메일 인증 코드가 있다면, 기존 코드 삭제
        if(hasEmailVerificationCode(email)) {
            redisTemplate.delete(key);
        }
        String randomCode = String.valueOf((int)((Math.random() * 900000) + 100000));
        redisTemplate.opsForValue().set(key, randomCode, 300, TimeUnit.SECONDS);
    }

    public String getEmailVerificationCode(String email) {
        String key = VERIFICATION_CODE.getKeyPrefix() + email;
        return redisTemplate.opsForValue().get(key);
    }

    public boolean hasEmailVerificationCode(String email) {
        String key = VERIFICATION_CODE.getKeyPrefix() + email;
        return redisTemplate.hasKey(key);
    }

    public void verifyEmailCode(String email, String code) {
        String key = VERIFICATION_CODE.getKeyPrefix() + email;
        String savedCode = redisTemplate.opsForValue().get(key);

        if(savedCode == null) {
            throw new CustomException(ErrorCode.VERIFICATION_CODE_NOT_FOUND);
        }

        if(!savedCode.equals(code)) {
            throw new CustomException(ErrorCode.VERIFICATION_CODE_MIS_MATCH);
        }

        redisTemplate.delete(VERIFICATION_CODE.getKeyPrefix() + email);
        saveEmailVerificationStatus(email);
    }

    private void saveEmailVerificationStatus(String email) {
        String key = EMAIL_VERIFIED.getKeyPrefix() + email;
        if(redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }
        redisTemplate.opsForValue().set(key, "true", 300, TimeUnit.SECONDS);
    }

    public void hasVerifiedEmail(String email) {
        String key = EMAIL_VERIFIED.getKeyPrefix() + email;
        if(!redisTemplate.hasKey(key)) {
            throw new CustomException(ErrorCode.EMAIL_NOT_VERIFIED);
        }
        redisTemplate.delete(key);
    }

}
