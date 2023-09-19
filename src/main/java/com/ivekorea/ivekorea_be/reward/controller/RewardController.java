package com.ivekorea.ivekorea_be.reward.controller;


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
    public ResponseEntity<?> iveAddList(@RequestParam Long ai, @RequestParam Long uid, @RequestParam String ads_name, @RequestParam String ads_type,
                                        @RequestParam Long reward, @RequestParam Long mda_reward,
                                        @RequestParam String key, @RequestParam String hash) {
        return ResponseEntity.ok().body(rewardService.saveReward(ai, uid, ads_name, ads_type,reward, mda_reward, key, hash));
    }
}
