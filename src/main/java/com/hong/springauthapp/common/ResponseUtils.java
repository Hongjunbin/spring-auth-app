package com.hong.springauthapp.common;

import com.hong.springauthapp.exception.ResponseExceptionEnum;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {

    public static ResponseEntity<HttpResponseDto> of(ResponseExceptionEnum responseExceptionEnum) {
        return ResponseEntity.status(responseExceptionEnum.getHttpStatus())
                .body(new HttpResponseDto(responseExceptionEnum.getHttpStatus().value(),
                        responseExceptionEnum.getMessage()));
    }

    public static ResponseEntity<HttpResponseDto> of(ResponseEnum responseEnum) {
        return ResponseEntity.status(responseEnum.getHttpStatus())
                .body(new HttpResponseDto(responseEnum.getHttpStatus().value(),
                        responseEnum.getMessage()));
    }

    public static ResponseEntity<HttpResponseDto> of(ResponseEnum responseEnum, Object data) {
        return ResponseEntity.status(responseEnum.getHttpStatus())
                .body(new HttpResponseDto(responseEnum.getHttpStatus().value(), responseEnum.getMessage(), data));
    }
}
