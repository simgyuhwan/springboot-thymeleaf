package com.practice.board.domain.user.entity;

import lombok.Getter;

@Getter
public enum Role {
    USER("사용자"), ADMIN("관리자"), MANAGER("매니저");

    private String role;

    Role(String role){
        this.role = role;
    }
}
