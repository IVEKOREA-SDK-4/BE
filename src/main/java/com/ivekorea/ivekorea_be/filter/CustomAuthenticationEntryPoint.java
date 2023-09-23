package com.ivekorea.ivekorea_be.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivekorea.ivekorea_be.common.dto.SendMessageDto;
import com.ivekorea.ivekorea_be.exception.CustomException;
import com.ivekorea.ivekorea_be.exception.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();

        log.error("인증 실패");

        CustomException exception = new CustomException(ErrorCode.INVALID_TOKEN);

        response.setStatus(exception.getErrorCode().getHttpStatus().value());
        response.setContentType("application/json;charset=UTF-8");
        objectMapper.writeValue(response.getWriter(), SendMessageDto.of(ErrorCode.INVALID_TOKEN));
    }
}