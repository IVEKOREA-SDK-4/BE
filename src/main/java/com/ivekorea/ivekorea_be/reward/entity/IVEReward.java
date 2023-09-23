package com.ivekorea.ivekorea_be.reward.entity;

import com.ivekorea.ivekorea_be.member.entity.Member;

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

    @ManyToOne
    @JoinColumn(name = "member_uid")
    private Member member;

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
    private String clickKey;

    // 클릭키 유효성 검증 해시
    @Column
    private String hash;

}
