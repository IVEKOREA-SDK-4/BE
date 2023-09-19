package com.ivekorea.ivekorea_be.reward.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@RequiredArgsConstructor
public class RewardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rewardId;

    @Column
    private Long uid;

    @Column
    private Long ai;

    @Column
    private String adsType;

    @Column
    private String adsName;

    @Column
    private Long reward;

    @Column
    private Long mdaReward;

    @Column
    private String key;

    @Column
    private String hash;

}
