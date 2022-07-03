package com.practice.board.config.security.entity;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Slf4j
public class SecurityUser extends User {
    private com.practice.board.domain.user.entity.User user;

    public SecurityUser(com.practice.board.domain.user.entity.User user){
        super(user.getUserId(), user.getUserPw(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        log.info("SecurityUser user.userId = {}", user.getUserId());
        log.info("SecurityUser user.passWd = {}", user.getUserPw());
        log.info("SecurityUser user.role = {}", user.getRole());
        this.user = user;
    }

    public com.practice.board.domain.user.entity.User getUser(){
        return this.user;
    }

    public String getUserId(){
        return user.getUserId();
    }

}
