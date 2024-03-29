package com.ivekorea.ivekorea_be.member.repository;

import com.ivekorea.ivekorea_be.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

}
