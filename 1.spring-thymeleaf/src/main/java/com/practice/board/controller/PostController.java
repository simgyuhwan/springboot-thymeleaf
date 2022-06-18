package com.practice.board.controller;

import com.practice.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService service;

    @GetMapping("/")
    public String test(){
        return "board/board";
    }

}
