package com.ivekorea.ivekorea_be.reward.controller;


import com.ivekorea.ivekorea_be.reward.entity.IVEReward;
import com.ivekorea.ivekorea_be.reward.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ive")
public class RewardController {

    private final RewardService rewardService;

    @GetMapping("/callback")
    public ResponseEntity<IVEReward> iveAddList(@RequestParam(name = "ads_idx") Long adsIdx,
                                                @RequestParam(name = "user") Long userId,
                                                @RequestParam(name = "ads_name") String adsName,
                                                @RequestParam(name = "ads_type") String adsType,
                                                @RequestParam Long reward,
                                                @RequestParam(name = "mda_reward") Long mdaReward,
                                                @RequestParam String key,
                                                @RequestParam String hash) {
        return ResponseEntity.ok().body(rewardService.saveIVEReward(adsIdx, userId, adsName, adsType,reward, mdaReward, key, hash));
    }
}
