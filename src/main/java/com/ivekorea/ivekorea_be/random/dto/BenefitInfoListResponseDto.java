package com.ivekorea.ivekorea_be.random.dto;

import com.ivekorea.ivekorea_be.random.entity.BenefitInfo;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BenefitInfoListResponseDto {

    private Long benefitInfoId;
    private String imageUrl;
    private String productName;
    private Integer salePrice;
    private Long benefitId;

    public static BenefitInfoListResponseDto of(BenefitInfo benefitInfo){
        return BenefitInfoListResponseDto.builder()
                .benefitInfoId(benefitInfo.getId())
                .imageUrl(benefitInfo.getImageUrl())
                .productName(benefitInfo.getProductName())
                .salePrice(benefitInfo.getSalePrice())
                .benefitId(benefitInfo.getId())
                .build();
    }
}
