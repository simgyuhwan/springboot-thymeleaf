package com.practice.board.domain.user.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SignUpDto {
    private Long id;

    @NotBlank
    @Size(max = 10, message = "이름은 최대 10자 입니다.")
    private String username;

    @NotBlank
    private String user_id;

    @NotBlank
    private String user_pw;

    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "이메일 형식에 맞춰주세요.")
    @NotBlank
    private String email;

    @Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}", message = "비밀번호 형식에 맞춰주세요.")
    @NotBlank
    private String phoneNum;
    private String address;

}
