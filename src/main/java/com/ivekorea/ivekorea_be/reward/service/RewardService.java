package com.ivekorea.ivekorea_be.reward.service;

import com.ivekorea.ivekorea_be.reward.entity.IVEReward;
import com.ivekorea.ivekorea_be.reward.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RewardService {

    private final RewardRepository rewardRepository;

    public IVEReward saveIVEReward(Long absIdx,
                                   Long userId,
                                   String adsName,
                                   String adsType,
                                   Long reward,
                                   Long mdaReward,
                                   String key,
                                   String hash) {
        IVEReward iveReward = IVEReward.builder()
                .adsIdx(absIdx)
                .key(key)
                .hash(hash)
                .adsName(adsName)
                .mdaReward(mdaReward)
                .adsType(adsType)
                .userId(userId)
                .userReward(reward)
                .build();
        return rewardRepository.save(iveReward);
    }

}
