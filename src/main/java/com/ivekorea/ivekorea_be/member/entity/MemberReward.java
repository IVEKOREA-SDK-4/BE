package com.ivekorea.ivekorea_be.member.entity;

import jakarta.persistence.*;
import lombok.Getter;



@Entity
@Getter
public class MemberReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_uid")
    private Member member;

    @Column
    private Integer totalReward;
}
