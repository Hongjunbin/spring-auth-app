package com.hong.springauthapp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hong.springauthapp.common.HttpResponse;
import com.hong.springauthapp.user.entity.User;
import com.hong.springauthapp.user.dto.LoginRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Objects;

@Slf4j(topic = "JwtAuthenticationFilter")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, ObjectMapper objectMapper) {
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
        setFilterProcessesUrl("/api/users/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!Objects.equals(request.getMethod(), "POST")) {
            throw new AuthenticationServiceException("잘못된 HTTP 요청 입니다.");
        }
        try {
            LoginRequest requestDto = objectMapper.readValue(request.getInputStream(), LoginRequest.class);

            return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                    requestDto.email(),
                    requestDto.password()
                )
            );
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain,
        Authentication authResult) throws IOException
    {
        User user = ((UserDetailsImpl) authResult.getPrincipal()).getUser();
        String accessToken = jwtUtil.createAccessToken(user);
        HttpResponse responseDto = new HttpResponse(HttpStatus.OK.value(), "로그인이 성공적으로 되었습니다.", accessToken);

        response.setHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        response.getWriter().write(objectMapper.writeValueAsString(responseDto));
    }

    @Override
    protected void unsuccessfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException failed) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String errorMessage = "잘못된 아이디 또는 비밀번호 입니다.";
        HttpResponse errorResponse = new HttpResponse(HttpStatus.UNAUTHORIZED.value(), errorMessage);

        String jsonResponse = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().print(jsonResponse);
    }
}
