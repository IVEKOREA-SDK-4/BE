package com.ivekorea.ivekorea_be.member.Repository;

import com.ivekorea.ivekorea_be.member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepsitory extends JpaRepository<Member,Long> {

}
