package com.ivekorea.ivekorea_be.random.controller;

//import com.ivekorea.ivekorea_be.random.service.MockService;
import com.ivekorea.ivekorea_be.random.service.RandomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/random")
public class RandomController {

    private final RandomService randomService;
//    private final MockService mockService;

    @GetMapping("/categories")
    public ResponseEntity<?> fetchCategory() {
        return randomService.getCategory();
    }

    @GetMapping("benefits")
    public ResponseEntity<?> fetchBenefit() {
        return randomService.getBenefit();
    }

    @PostMapping("/draw-benefit")
    public ResponseEntity<?> fetchDrawBenefit() {
        return randomService.getDrawBenefit();
    }

    @PostMapping("/draw-piece")
    public ResponseEntity<?> fetchDrawResultPiece() {
        return randomService.getDrawResultPiece();
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
