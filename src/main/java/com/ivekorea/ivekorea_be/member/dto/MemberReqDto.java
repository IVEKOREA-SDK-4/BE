package com.ivekorea.ivekorea_be.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberReqDto {

    private String id;
    private String password;
}
