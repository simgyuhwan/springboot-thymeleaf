package com.practice.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login/signIn";
    }

    @PostMapping("/login")
    public String login(Model model){
        return "login/test";
    }

    @GetMapping("/signup")
    public String signup(){
        return "login/signUp";
    }

}
