package com.ivekorea.ivekorea_be.random.service;

import com.ivekorea.ivekorea_be.exception.CustomException;
import com.ivekorea.ivekorea_be.exception.ErrorCode;
import com.ivekorea.ivekorea_be.member.entity.Member;
import com.ivekorea.ivekorea_be.member.repository.MemberRewardRepository;
import com.ivekorea.ivekorea_be.random.draw.DrawPieceAlgorithm;
import com.ivekorea.ivekorea_be.random.draw.Level;
import com.ivekorea.ivekorea_be.random.draw.Pair;
import com.ivekorea.ivekorea_be.random.dto.BenefitInfoListResponseDto;
import com.ivekorea.ivekorea_be.random.dto.PieceResponseDto;
import com.ivekorea.ivekorea_be.random.dto.RandomResponseDto;
import com.ivekorea.ivekorea_be.random.entity.*;
import com.ivekorea.ivekorea_be.random.repository.*;
import com.ivekorea.ivekorea_be.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RandomService {
    private final MemberRewardRepository memberRewardRepository;

    private final CategoryRepository categoryRepository;
    private final BenefitRepository benefitRepository;
    private final BenefitInfoRepository benefitInfoRepository;
    private final DrawLogRepository drawLogRepository;
    private final PieceRepository pieceRepository;
    private final ExchangeLogRepository exchangeLogRepository;

    private final Random random = new Random();
    private final PieceRepository pieceRepository;

    public ResponseEntity<List<Category>> getCategory() {
        List<Category> categories = categoryRepository.findAll();

        return ResponseEntity.ok().body(categories);
    }

    public ResponseEntity<?> getBenefit() {
        return ResponseEntity.ok().body("ok");
    }

    @Transactional
    public ResponseEntity<RandomResponseDto.DrawBenefitDto> getDrawBenefit(Member member, Long pieceId) {
        Piece piece = pieceRepository.findByIdAndMember(pieceId, member).orElseThrow(
                () -> new CustomException(ErrorCode.UNDEFINED_REQUEST)
        );

        Level level = piece.getBenefit().getLevel();
        if (piece.getCount() <= level.getMaxCount()) {
            throw new CustomException(ErrorCode.UNDEFINED_REQUEST);
        }

        piece.deductPiece(level.getMaxCount());
        pieceRepository.save(piece);

        List<BenefitInfo> benefitInfos = benefitInfoRepository.findByBenefit(piece.getBenefit());

        BenefitInfo benefitInfo = benefitInfos.get(random.nextInt(0, benefitInfos.size() - 1));
        exchangeLogRepository.save(ExchangeLog.builder()
                .benefitInfo(benefitInfo)
                .member(member)
                .build());

        return ResponseEntity.ok().body(RandomResponseDto.DrawBenefitDto.builder()
                .benefitImage(benefitInfo.getImageUrl())
                .benefitName(benefitInfo.getProductName())
                .benefitPrice(benefitInfo.getSalePrice())
                .build());
    }

    @Transactional
    public ResponseEntity<RandomResponseDto.DrawPieceResultDto> getDrawResultPiece(Member member) {
        int count = drawLogRepository.countDrawLogByMember(member);
        log.info("유저의 조각 뽑기 카운트 : " + count);

        List<Pair<String, Integer>> target = getPairs(count);

        DrawPieceAlgorithm drawPieceAlgorithm = new DrawPieceAlgorithm(target);

        String word = drawPieceAlgorithm.getRandom();

        member.deductMemberReward();
        memberRewardRepository.save(member.getMemberReward());

        if (word.equals("꽝")) {
            return ResponseEntity.ok().body(new RandomResponseDto.DrawPieceResultDto("꽝이네요", word));
        }

        Category category = categoryRepository.findById(random.nextLong(1, 3)).orElseThrow(
                () -> new CustomException(ErrorCode.INVALID_CODE)
        );

        List<Benefit> benefits = benefitRepository.findByCategoryAndLevel(category, Level.valueOf(word));

        Benefit benefit = benefits.get(random.nextInt(0, benefits.size() - 1));

        drawLogRepository.save(DrawLog.builder()
                .benefit(benefit)
                .member(member)
                .build());

        Optional<Piece> optionalPiece = pieceRepository.findByMemberAndBenefit(member, benefit);

        if (optionalPiece.isEmpty()) {
            pieceRepository.save(Piece.builder()
                    .benefit(benefit)
                    .count(1)
                    .member(member)
                    .build());
        } else {
            optionalPiece.get().rewardPiece();
            pieceRepository.save(optionalPiece.get());
        }

        return ResponseEntity.ok().body(new RandomResponseDto.DrawPieceResultDto(category.getName(), word));
    }

    private List<Pair<String, Integer>> getPairs(int count) {
        int leverPercentageSum = 0;

        if (count != 0 && count % 10 == 0) {

            return Arrays.asList(
                    new Pair<>(Level.HIGH.name(), Level.HIGH.getPercentage()),
                    new Pair<>(Level.MIDDLE.name(), Level.MIDDLE.getPercentage()),
                    new Pair<>(Level.LOW.name(), Level.LOW.getPercentage())
            );

        } else {

            for (Level level : Level.values()) {
                leverPercentageSum += level.getPercentage();
            }

            return Arrays.asList(
                    new Pair<>(Level.HIGH.name(), Level.HIGH.getPercentage()),
                    new Pair<>(Level.MIDDLE.name(), Level.MIDDLE.getPercentage()),
                    new Pair<>(Level.LOW.name(), Level.LOW.getPercentage()),
                    new Pair<>("꽝", leverPercentageSum + (leverPercentageSum / 3) * Level.values().length)
            );
        }
    }

    public ResponseEntity<?> getDrawLog() {
        return ResponseEntity.ok().body("ok");
    }

    public Page<BenefitInfoListResponseDto> getBenefitInfo(Pageable pageable) {
        Page<BenefitInfo> pages = benefitInfoRepository.findAll(pageable);
        List<BenefitInfoListResponseDto> responseDtos = pages.stream()
                .map(BenefitInfoListResponseDto::of)
                .collect(Collectors.toList());
        return new PageImpl<>(responseDtos, pages.getPageable(), pages.getTotalElements());
    }

    public ResponseEntity<List<PieceResponseDto>> getMyHavePiece(UserDetailsImpl userDetails) {
        List<Piece> pieceList = pieceRepository.findByMember_Uid(userDetails.getUser().getUid());
        List<PieceResponseDto> pieceResponseDto = pieceList.stream()
                .map(PieceResponseDto::of)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(pieceResponseDto);
    }
}
