package com.ivekorea.ivekorea_be.random.repository;

import com.ivekorea.ivekorea_be.random.entity.BenefitInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenefitInfoRepository extends JpaRepository<BenefitInfo, Long> {

    Page<BenefitInfo> findAll(Pageable pageable);
}
