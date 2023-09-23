package com.ivekorea.ivekorea_be.random.dto;

import com.ivekorea.ivekorea_be.random.entity.Benefit;
import com.ivekorea.ivekorea_be.random.entity.BenefitInfo;
import com.ivekorea.ivekorea_be.random.entity.Piece;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PieceResponseDto {
    private Long pieceId;
    private Integer count;
    private Long benefitId;

    public static PieceResponseDto of(Piece piece){
        return PieceResponseDto.builder()
                .pieceId(piece.getId())
                .count(piece.getCount())
                .benefitId(piece.getBenefit().getId())
                .build();
    }
}
