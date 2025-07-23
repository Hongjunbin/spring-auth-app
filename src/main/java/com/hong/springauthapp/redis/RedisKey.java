package com.hong.springauthapp.redis;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RedisKey {
    VERIFICATION_CODE("verification_code:"),
    EMAIL_VERIFIED("verification_verified:");

    private final String keyPrefix;
}
