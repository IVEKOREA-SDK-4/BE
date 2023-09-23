package com.ivekorea.ivekorea_be.random.repository;

import com.ivekorea.ivekorea_be.random.entity.Benefit;
import com.ivekorea.ivekorea_be.random.entity.BenefitInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BenefitInfoRepository extends JpaRepository<BenefitInfo, Long> {

    List<BenefitInfo> findByBenefit(Benefit benefit);

    Page<BenefitInfo> findAll(Pageable pageable);
}
