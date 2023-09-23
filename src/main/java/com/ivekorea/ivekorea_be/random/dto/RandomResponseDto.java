package com.ivekorea.ivekorea_be.random.dto;

import com.ivekorea.ivekorea_be.random.draw.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class RandomResponseDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class DrawPieceResultDto {

        private final String category;
        private final String level;

    }

}
