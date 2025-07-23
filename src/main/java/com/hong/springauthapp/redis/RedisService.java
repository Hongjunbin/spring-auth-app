package com.hong.springauthapp.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

import static com.hong.springauthapp.redis.RedisKey.*;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public void createVerificationCode(String email) {
        String randomCode = String.valueOf((int)((Math.random() * 900000) + 100000));
        String key = VERIFICATION_CODE.getKeyPrefix() + email;
        redisTemplate.opsForValue().set(key, randomCode, 300, TimeUnit.SECONDS);
    }

    public String getVerificationCode(String email) {
        String key = VERIFICATION_CODE.getKeyPrefix() + email;
        return redisTemplate.opsForValue().get(key);
    }

    public void checkVerificationCode() {

    }

}
