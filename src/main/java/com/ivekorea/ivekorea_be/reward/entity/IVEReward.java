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
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long adsIdx;

    @Column
    private String adsType;

    @Column
    private String adsName;

    @Column
    private Long userReward;

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
