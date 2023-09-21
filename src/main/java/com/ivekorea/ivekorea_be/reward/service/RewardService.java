package com.ivekorea.ivekorea_be.reward.service;

import com.ivekorea.ivekorea_be.reward.entity.IVEReward;
import com.ivekorea.ivekorea_be.reward.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RewardService {

    private final RewardRepository rewardRepository;

    public IVEReward saveReward(Long ai, Long uid, String ads_name, String ads_type,
                                Long reward, Long mda_reward,
                                String key, String hash) {
        IVEReward IVERewardEntity = IVEReward.builder()
                .ai(ai)
                .key(key)
                .hash(hash)
                .adsName(ads_name)
                .mdaReward(mda_reward)
                .adsType(ads_type)
                .uid(uid)
                .reward(reward)
                .build();
        return rewardRepository.save(IVERewardEntity);
    }

}
