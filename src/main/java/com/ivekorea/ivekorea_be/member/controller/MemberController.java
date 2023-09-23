package com.ivekorea.ivekorea_be.member.controller;

import com.ivekorea.ivekorea_be.member.dto.MemberRequestDto;
import com.ivekorea.ivekorea_be.member.service.MemberService;
import com.ivekorea.ivekorea_be.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody MemberRequestDto memberRequestDto) {
        return memberService.saveMember(memberRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberRequestDto memberRequestDto, HttpServletResponse response) {
        return memberService.compareMember(memberRequestDto, response);
    }

    @GetMapping("/reward")
    public ResponseEntity<?> totalReward(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memberService.totalReward(userDetails);
    }

}