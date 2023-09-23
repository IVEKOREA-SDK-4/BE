package com.ivekorea.ivekorea_be.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column
    private String id;

    @Column
    private String password;

    @Column
    private String socialType;

    @OneToOne
    @JoinColumn(name = "member_reward_id")
    private MemberReward memberReward;

    public void deductMemberReward() {
        memberReward.deductTotalReward(100);
    }
}
