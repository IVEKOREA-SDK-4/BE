package com.ivekorea.ivekorea_be.reward.service;

import com.ivekorea.ivekorea_be.exception.CustomException;
import com.ivekorea.ivekorea_be.exception.ErrorCode;
import com.ivekorea.ivekorea_be.member.entity.Member;
import com.ivekorea.ivekorea_be.member.repository.MemberRepository;
import com.ivekorea.ivekorea_be.reward.entity.IVEReward;
import com.ivekorea.ivekorea_be.reward.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RewardService {

    /*
        TODO userId 활용해서 Member로 저장
     */

    private final RewardRepository rewardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ResponseEntity<String> saveIVEReward(Long adsIdx,
                                        Long userId,
                                        String adsName,
                                        String adsType,
                                        Long reward,
                                        Long mdaReward,
                                        String key,
                                        String hash) {
        checkHash(key, hash);

        Member member = memberRepository.findByUid(userId).orElseThrow(
                () -> new CustomException(ErrorCode.WRONG_USER)
        );

        IVEReward iveReward = IVEReward.builder()
                .adsIdx(adsIdx)
                .clickKey(key)
                .hash(hash)
                .adsName(adsName)
                .mdaReward(mdaReward)
                .adsType(adsType)
                .member(member)
                .userReward(reward)
                .build();

        rewardRepository.save(iveReward);

        // TODO 멤버 리워드 적립

        // 200을 보내야 재요청을 하지 않음
        return ResponseEntity.ok("ok");
    }

    private void checkHash(String key, String hash) {
        try {
            String appCodeAndKey = "e74cScsKMH" + key;

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(appCodeAndKey.getBytes(StandardCharsets.UTF_8));

            byte[] digest = messageDigest.digest();

            StringBuilder hexString = new StringBuilder();

            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }

            if (!hash.contentEquals(hexString)) {
                throw new CustomException(ErrorCode.ACCESS_DENIED);
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
