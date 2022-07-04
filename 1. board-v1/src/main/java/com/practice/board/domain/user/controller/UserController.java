package com.practice.board.domain.user.controller;

import com.practice.board.domain.user.dto.SignInDto;
import com.practice.board.domain.user.dto.SignUpDto;
import com.practice.board.domain.user.service.UserService;
import com.practice.board.exception.UserIdAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signIn")
    public String login(Model model){
        //model.addAttribute("signInDto", new SignInDto());
        return "login/signIn";
    }

    @GetMapping("/signIn/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "login/signIn";
    }

    @GetMapping("/signUp")
    public String signup(Model model) {
        model.addAttribute("signUpDto", new SignUpDto());
        return "login/signUp";
    }

    @PostMapping("/signUp")
    public String signup(Model model, @Validated SignUpDto signUpDto,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "login/signUp";
        }

        userService.signUp(signUpDto);
        return "redirect:/board";
    }

    @GetMapping("/valid/{userId}")
    @ResponseBody
    public boolean DuplicateUserId(@PathVariable String userId){
        boolean isDuplicate = userService.duplicateUserId(userId);
        return isDuplicate;
    }

}
