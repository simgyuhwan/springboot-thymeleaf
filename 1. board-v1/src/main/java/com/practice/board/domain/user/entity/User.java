package com.practice.board.domain.user.entity;

import com.practice.board.domain.common.entity.BaseEntity;
import lombok.AccessLevel;
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
    private String user_id;
    private String user_pw;

    @Column()
    private String username;

    @Column(unique = true)
    private String email;
    private String phoneNum;
    private String address;

}
