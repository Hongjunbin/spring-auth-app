package com.hong.springauthapp.redis;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RedisKey {
    VERIFICATION_CODE("verification_code:");

    private final String keyPrefix;
}
