package com.practice.board.domain.user.dto;

import com.practice.board.domain.user.entity.Role;
import com.practice.board.domain.user.entity.User;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.*;

@Data
public class SignUpDto {
    private Long id;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Size(max = 10, message = "이름은 최대 10자 입니다.")
    private String username;

    @NotBlank(message = "ID는 필수 입력 값입니다.")
    private String userId;

    @NotBlank(message = "PW는 필수 입력 값입니다.")
    @Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하입니다.")
    private String userPw;

    private String userPw2;

    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "이메일 형식에 맞춰주세요.")
    @Email
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}", message = "핸드폰 번호 형식에 맞춰주세요.")
    @NotBlank(message = "핸드폰 번호는 필수 입력 값입니다.")
    private String phoneNum;
    private String address;

    public User toEntityWithRole(PasswordEncoder passwordEncoder, Role role){
        return User.builder()
                .id(id)
                .user_id(userId)
                .user_pw(passwordEncoder.encode(userPw))
                .email(email)
                .phoneNum(phoneNum)
                .address(address)
                .role(role)
                .build();
    }

}
