package com.ivekorea.ivekorea_be.random.controller;

//import com.ivekorea.ivekorea_be.random.service.MockService;
import com.ivekorea.ivekorea_be.random.dto.RandomResponseDto;
import com.ivekorea.ivekorea_be.random.entity.Category;
import com.ivekorea.ivekorea_be.random.service.RandomService;
import com.ivekorea.ivekorea_be.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("benefits")
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


//
//    @PostMapping("/mock")
//    public ResponseEntity<String> crawlMock() {
//        return ResponseEntity.ok().body(mockService.saveMock());
//    }

}
