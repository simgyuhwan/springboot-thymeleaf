package com.practice.board.domain.user.entity;

import com.practice.board.domain.common.entity.BaseEntity;
import com.practice.board.domain.user.dto.SignUpDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userId;
    private String userPw;

    @Column()
    private String username;

    @Column(unique = true)
    private String email;
    private String phoneNum;
    private String address;

    @Builder
    public User(Long id, String user_id, String user_pw, String username, String email, String phoneNum, String address) {
        this.id = id;
        this.userId = user_id;
        this.userPw = user_pw;
        this.username = username;
        this.email = email;
        this.phoneNum = phoneNum;
        this.address = address;
    }


}
