package com.ivekorea.ivekorea_be.member.service;

import com.ivekorea.ivekorea_be.member.Entity.Member;
import com.ivekorea.ivekorea_be.member.Repository.MemberRepsitory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepsitory memberRepsitory;

    @Transactional
    public Member saveMember(String id, String password) {
        Member member = Member.builder()
                .id(id)
                .password(password)
                .build();
        return memberRepsitory.save(member);
    }
}
