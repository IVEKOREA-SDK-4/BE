package com.ivekorea.ivekorea_be.member.repository;

import com.ivekorea.ivekorea_be.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findById(String id);

    Optional<Member> findByUid(Long uid);
}
