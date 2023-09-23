package com.ivekorea.ivekorea_be.common.dto;

import com.ivekorea.ivekorea_be.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class SendMessageDto {
    
    private final String message;
    private final String code;
    private final int statusCode;

    @Builder
    public SendMessageDto(String message, String code, int statusCode) {
        this.message = message;
        this.code = code;
        this.statusCode = statusCode;
    }

    public static SendMessageDto of(ErrorCode errorCode) {
        return SendMessageDto.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .statusCode(errorCode.getHttpStatus().value())
                .build();
    }

    public static ResponseEntity<SendMessageDto> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus().value())
                .body(SendMessageDto.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .statusCode(errorCode.getHttpStatus().value())
                        .build());
    }
}
