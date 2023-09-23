package com.ivekorea.ivekorea_be.random.entity;


import com.ivekorea.ivekorea_be.member.entity.Member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class DrawLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_uid")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "benefit_id")
    private Benefit benefit;

}
