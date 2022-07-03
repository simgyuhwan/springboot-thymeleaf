package com.practice.board.domain.user.controller;

import com.practice.board.domain.user.dto.SignInDto;
import com.practice.board.domain.user.dto.SignUpDto;
import com.practice.board.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signIn")
    public String login(Model model){
        model.addAttribute("signInDto", new SignInDto());
        return "login/signIn";
    }

    @PostMapping("/signIn")
    public String login(Model model, SignInDto signInDto){
        return "redirect:/board";
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

}
