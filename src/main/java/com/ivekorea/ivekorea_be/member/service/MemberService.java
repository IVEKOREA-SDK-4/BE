package com.ivekorea.ivekorea_be.member.service;

import com.ivekorea.ivekorea_be.member.entity.Member;
import com.ivekorea.ivekorea_be.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member saveMember(String id, String password) {
        Member member = Member.builder()
                .id(id)
                .password(password)
                .build();
        return memberRepository.save(member);
    }
}
