package com.practice.board.domain.user.entity;

import com.practice.board.domain.common.entity.BaseEntity;
import com.practice.board.domain.user.dto.SignUpDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(unique = true, name = "user_id")
    private String userId;

    @Column(name = "user_pw")
    private String userPw;

    @Column(name = "username")
    private String username;

    @Column(unique = true, name="email")
    private String email;

    @Column(name="phone_num")
    private String phoneNum;

    @Column(name="address")
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(Long id, String user_id, String user_pw, String username, String email, String phoneNum, String address, Role role) {
        this.id = id;
        this.userId = user_id;
        this.userPw = user_pw;
        this.username = username;
        this.email = email;
        this.phoneNum = phoneNum;
        this.address = address;
        this.role = role;
    }



}
