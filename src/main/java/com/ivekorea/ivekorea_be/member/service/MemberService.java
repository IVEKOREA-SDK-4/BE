package com.ivekorea.ivekorea_be.member.service;

import com.ivekorea.ivekorea_be.exception.CustomException;
import com.ivekorea.ivekorea_be.exception.ErrorCode;
import com.ivekorea.ivekorea_be.member.dto.MemberRequestDto;
import com.ivekorea.ivekorea_be.member.dto.MemberRewardResponseDto;
import com.ivekorea.ivekorea_be.member.entity.Member;
import com.ivekorea.ivekorea_be.member.entity.MemberReward;
import com.ivekorea.ivekorea_be.member.repository.MemberRepository;
import com.ivekorea.ivekorea_be.member.repository.MemberRewardRepository;
import com.ivekorea.ivekorea_be.provider.JwtProvider;
import com.ivekorea.ivekorea_be.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberRewardRepository memberRewardRepository;
    private final JwtProvider jwtProvider;
    private final StringEncryptor jasyptStringEncryptor;

    @Transactional
    public ResponseEntity<String> saveMember(MemberRequestDto memberRequestDto) {
        MemberReward memberReward = memberRewardRepository.save(new MemberReward(10000));

        Member member = Member.builder()
                .id(memberRequestDto.getId())
                .password(jasyptStringEncryptor.encrypt(memberRequestDto.getPassword()))
                .memberReward(memberReward)
                .build();
        memberRepository.save(member);

        return ResponseEntity.status(HttpStatus.CREATED).body("ok");
    }

    @Transactional(readOnly = true)
    public ResponseEntity<String> compareMember(MemberRequestDto memberRequestDto, HttpServletResponse response) {
        Member member = memberRepository.findById(memberRequestDto.getId()).orElseThrow(
                () -> new CustomException(ErrorCode.UNREGISTER_USER)
        );

        String decryptPassword = jasyptStringEncryptor.decrypt(member.getPassword());
        if (!memberRequestDto.getPassword().equals(decryptPassword)) {
            throw new CustomException(ErrorCode.UNREGISTER_USER);
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", memberRequestDto.getId());
        claims.put("uid", member.getUid());
        String accessToken = jwtProvider.createToken(claims, jwtProvider.getExpireDateAccessToken());
        String refreshToken = jwtProvider.createToken(new HashMap<>(), jwtProvider.getExpireDateRefreshToken());

        response.setHeader(JwtProvider.AUTHORIZATION_HEADER, accessToken);
        response.addHeader("Refresh-Token", refreshToken);

        return ResponseEntity.status(HttpStatus.CREATED).body("ok");
    }

    public ResponseEntity<?> totalReward(UserDetailsImpl userDetails) {
        Member member = memberRepository.findByUid(userDetails.getUser().getUid()).orElseThrow(
                () -> new CustomException(ErrorCode.INVALID_CODE)
        );
        MemberRewardResponseDto memberRewardResponseDto = MemberRewardResponseDto.builder()
                .totalReward(member.getMemberReward().getTotalReward())
                .build();
        return ResponseEntity.ok().body(memberRewardResponseDto);
    }
}
