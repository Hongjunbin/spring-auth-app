package com.hong.springauthapp.user.dto;

public record VerificationRequest(String email, String code) {
}
