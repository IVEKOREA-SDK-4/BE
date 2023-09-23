package com.ivekorea.ivekorea_be.random.repository;

import com.ivekorea.ivekorea_be.random.entity.Benefit;
import com.ivekorea.ivekorea_be.random.entity.BenefitInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BenefitInfoRepository extends JpaRepository<BenefitInfo, Long> {

    List<BenefitInfo> findByBenefit(Benefit benefit);
}
