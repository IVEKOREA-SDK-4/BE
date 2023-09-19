package com.ivekorea.ivekorea_be.member.controller;

import com.ivekorea.ivekorea_be.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public ResponseEntity<?> signup(String id, String password){
        return ResponseEntity.ok().body(memberService.saveMember(id, password));
    }
}
