package com.ivekorea.ivekorea_be.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class MemberReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer totalReward;


    public MemberReward(Integer totalReward) {
        this.totalReward = totalReward;
    }

    public void deductTotalReward(int useReward) {
        totalReward -= useReward;
    }

    public void plusReward(int reward) {
        totalReward += reward;
    }
}
