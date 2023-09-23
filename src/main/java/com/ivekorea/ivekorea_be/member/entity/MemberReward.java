package com.ivekorea.ivekorea_be.member.entity;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
public class MemberReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer totalReward;


    public void deductTotalReward(int useReward) {
        totalReward -= useReward;
    }
}
