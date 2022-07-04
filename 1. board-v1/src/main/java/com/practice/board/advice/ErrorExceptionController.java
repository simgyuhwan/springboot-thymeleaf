package com.practice.board.advice;

import com.practice.board.exception.UserIdAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ErrorExceptionController {

    @ExceptionHandler(UserIdAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String usernameAlreadyExistException(UserIdAlreadyExistsException e){
      log.error("error : {} ", e.getMessage());
      return "login/signUp";
    }
}
