package com.hong.springauthapp.common;

import com.hong.springauthapp.exception.ErrorCode;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {

    public static ResponseEntity<HttpResponse> of(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(new HttpResponse(errorCode.getHttpStatus().value(),
                        errorCode.getMessage()));
    }

    public static ResponseEntity<HttpResponse> of(ResponseEnum responseEnum) {
        return ResponseEntity.status(responseEnum.getHttpStatus())
                .body(new HttpResponse(responseEnum.getHttpStatus().value(),
                        responseEnum.getMessage()));
    }

    public static ResponseEntity<HttpResponse> of(ResponseEnum responseEnum, Object data) {
        return ResponseEntity.status(responseEnum.getHttpStatus())
                .body(new HttpResponse(responseEnum.getHttpStatus().value(), responseEnum.getMessage(), data));
    }
}
