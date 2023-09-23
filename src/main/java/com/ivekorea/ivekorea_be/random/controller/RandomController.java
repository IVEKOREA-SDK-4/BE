package com.ivekorea.ivekorea_be.random.controller;

//import com.ivekorea.ivekorea_be.random.service.MockService;

import com.ivekorea.ivekorea_be.random.dto.BenefitInfoListResponseDto;
import com.ivekorea.ivekorea_be.random.dto.PieceResponseDto;
import com.ivekorea.ivekorea_be.random.dto.RandomResponseDto;
import com.ivekorea.ivekorea_be.random.entity.Category;
import com.ivekorea.ivekorea_be.random.service.RandomService;
import com.ivekorea.ivekorea_be.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/random")
public class RandomController {

    private final RandomService randomService;
//    private final MockService mockService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> fetchCategory() {
        return randomService.getCategory();
    }

    @GetMapping("/benefits")
    public ResponseEntity<?> fetchBenefit() {
        return randomService.getBenefit();
    }

    @PostMapping("/draw-benefit/{pieceId}")
    public ResponseEntity<RandomResponseDto.DrawBenefitDto> fetchDrawBenefit(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long pieceId) {
        return randomService.getDrawBenefit(userDetails.getUser(), pieceId);
    }

    @PostMapping("/draw-piece")
    public ResponseEntity<RandomResponseDto.DrawPieceResultDto> fetchDrawResultPiece(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return randomService.getDrawResultPiece(userDetails.getUser());
    }

    @GetMapping("/draw-logs")
    public ResponseEntity<?> fetchDrawLog() {
        return randomService.getDrawLog();
    }

    @GetMapping("/benefitInfo")
    public ResponseEntity<?> benefitInfo(@PageableDefault(size = 20, page = 1) Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());

        Page<BenefitInfoListResponseDto> responseDtos = randomService.getBenefitInfo(pageRequest);

        return ResponseEntity.ok().body(responseDtos);
    }

    @GetMapping("/mypiece")
    public ResponseEntity<List<PieceResponseDto>> myHavePiece(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return randomService.getMyHavePiece(userDetails);
    }

//
//    @PostMapping("/mock")
//    public ResponseEntity<String> crawlMock() {
//        return ResponseEntity.ok().body(mockService.saveMock());
//    }

}
