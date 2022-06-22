package com.practice.board.controller;

import com.practice.board.dto.PostDto;
import com.practice.board.entity.Posts;
import com.practice.board.mapper.PostMapper;
import com.practice.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class PostController {
    private final PostService service;

    @GetMapping
    public String list(Model model){
        String writer = new String("test");
        model.addAttribute("writer",writer);
        return "board/board";
    }

    @GetMapping("/post")
    public String write(Model model){
        model.addAttribute("PostDto",new PostDto());
        return "board/write";
    }

    @PostMapping("/post")
    public String write(Model model, @Validated PostDto postDto){
        Posts posts = service.addPosts(postDto);
        model.addAttribute("PostDto",PostMapper.MAPPER.toDto(posts));
        return "board/detail";
    }

}
