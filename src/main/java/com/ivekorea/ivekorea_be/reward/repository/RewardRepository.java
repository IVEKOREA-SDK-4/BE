package com.ivekorea.ivekorea_be.reward.repository;

import com.ivekorea.ivekorea_be.reward.entity.IVEReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepository extends JpaRepository<IVEReward, Long> {
}
