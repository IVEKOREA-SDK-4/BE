package com.ivekorea.ivekorea_be.random.service;

import com.ivekorea.ivekorea_be.random.draw.DrawPieceAlgorithm;
import com.ivekorea.ivekorea_be.random.draw.Level;
import com.ivekorea.ivekorea_be.random.draw.Pair;
import com.ivekorea.ivekorea_be.random.dto.BenefitInfoListResponseDto;
import com.ivekorea.ivekorea_be.random.entity.BenefitInfo;
import com.ivekorea.ivekorea_be.random.repository.BenefitInfoRepository;
import com.ivekorea.ivekorea_be.random.repository.BenefitRepository;
import com.ivekorea.ivekorea_be.random.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RandomService {

    private final CategoryRepository categoryRepository;
    private final BenefitRepository benefitRepository;
    private final BenefitInfoRepository benefitInfoRepository;

    public ResponseEntity<?> getCategory() {
        return ResponseEntity.ok().body("ok");
    }

    public ResponseEntity<?> getBenefit() {
        return ResponseEntity.ok().body("ok");
    }

    public ResponseEntity<?> getDrawBenefit() {
        return ResponseEntity.ok().body("ok");
    }

    public ResponseEntity<?> getDrawResultPiece() {
        int leverPercentageSum = 0;

        for (Level level : Level.values()) {
            leverPercentageSum += level.getPercentage();
        }

        List<Pair<String, Integer>> target = Arrays.asList(
                new Pair<>(Level.HIGH.name(), Level.HIGH.getPercentage()),
                new Pair<>(Level.MIDDLE.name(), Level.MIDDLE.getPercentage()),
                new Pair<>(Level.LOW.name(), Level.LOW.getPercentage()),
                new Pair<>("꽝", leverPercentageSum + (leverPercentageSum / 3) * Level.values().length)
        );

        DrawPieceAlgorithm drawPieceAlgorithm = new DrawPieceAlgorithm(target);

        // 1만번 랜덤 추출 테스트
        int count = 10000;
        Map<String, Integer> wordCount = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String word = drawPieceAlgorithm.getRandom();
            wordCount.put(word, 1 + wordCount.getOrDefault(word, 0));
        }

        return ResponseEntity.ok().body(wordCount);
    }

    public ResponseEntity<?> getDrawLog() {
        return ResponseEntity.ok().body("ok");
    }

    public Page<BenefitInfoListResponseDto> getBenefitInfo(Pageable pageable){
        Page<BenefitInfo> pages = benefitInfoRepository.findAll(pageable);
        List<BenefitInfoListResponseDto> responseDtos = pages.stream()
                .map(BenefitInfoListResponseDto::of)
                .collect(Collectors.toList());
        return new PageImpl<>(responseDtos, pages.getPageable(), pages.getTotalElements());
    }
}
