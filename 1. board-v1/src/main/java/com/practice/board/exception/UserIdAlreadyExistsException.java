package com.practice.board.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserIdAlreadyExistsException extends RuntimeException{
    public UserIdAlreadyExistsException(String message) {
        super(message);
        log.error("UserIdAlreadyExistsException userId={} 는 등록된 Id입니다.", message);
    }
}
