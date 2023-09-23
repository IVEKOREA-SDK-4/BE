package com.ivekorea.ivekorea_be.random.repository;

import com.ivekorea.ivekorea_be.member.entity.Member;
import com.ivekorea.ivekorea_be.random.entity.DrawLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrawLogRepository extends JpaRepository<DrawLog, Long> {
    int countDrawLogByMember(Member member);
}
