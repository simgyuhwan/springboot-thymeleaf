package com.practice.board.domain.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

// 사용 안함
@Data
public class SignInDto {

    private Long id;

    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String userPw;
}
