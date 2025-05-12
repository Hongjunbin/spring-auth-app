package com.hong.springauthapp.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpResponseDto {

    private Integer statusCode;
    private String message;
    private Object data;

    public HttpResponseDto(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public HttpResponseDto(Integer statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}
