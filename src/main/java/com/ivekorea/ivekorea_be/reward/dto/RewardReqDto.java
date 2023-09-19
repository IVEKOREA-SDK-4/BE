package com.ivekorea.ivekorea_be.reward.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RewardReqDto {

    private Long uid;
    private Long ai;
    private String adsType;
    private String adsName;
    private Long reward;
    private Long mdaReward;
    private String key;
    private String hash;
}
