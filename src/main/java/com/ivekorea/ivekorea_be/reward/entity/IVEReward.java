package com.ivekorea.ivekorea_be.reward.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IVEReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rewardId;

    // 유저 아이디
    @Column
    private Long uid;

    // 광고 번호
    @Column
    private Long ai;

    // 광고 타입
    @Column
    private String adsType;

    // 광고 이름
    @Column
    private String adsName;

    // 유저 적립 포인트
    @Column
    private Long reward;

    // 매체 적립금
    @Column
    private Long mdaReward;

    // 클릭키
    @Column
    private String key;

    // 클릭키 유효성 검증 해시
    @Column
    private String hash;

}
