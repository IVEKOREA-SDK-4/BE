package com.ivekorea.ivekorea_be.random.entity;

import com.ivekorea.ivekorea_be.member.entity.Member;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_uid")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "benefit_id")
    private Benefit benefit;

    @Column
    private Integer count;

}
