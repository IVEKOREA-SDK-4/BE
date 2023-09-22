package com.ivekorea.ivekorea_be.random.entity;

import com.ivekorea.ivekorea_be.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class DrawLog {

    /*
        TODO: createdDate 상속으로 추가
     */

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
