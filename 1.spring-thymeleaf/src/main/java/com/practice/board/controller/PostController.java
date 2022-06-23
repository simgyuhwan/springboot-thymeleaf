package com.practice.board.controller;

import com.practice.board.dto.PostDto;
import com.practice.board.entity.Posts;
import com.practice.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class PostController {
    private final PostService service;

    @GetMapping
    public String list(Model model){
        model.addAttribute("postDtoList", service.getPosts());
        return "board/board";
    }

    @GetMapping("/post")
    public String write(Model model){
        model.addAttribute("PostDto",new PostDto());
        return "board/write";
    }

    @PostMapping("/post")
    public String write(Model model, @Validated PostDto postDto){
        model.addAttribute("PostDto", PostDto.of(service.addPosts(postDto)));
        return "board/detail";
    }

    @GetMapping("/{postId}")
    public String update(@PathVariable Long postId, Model model){
        model.addAttribute("PostDto", PostDto.of(service.getPost(postId)));
        return "board/update";
    }

    @PostMapping("/{postId}")
    public String update(@PathVariable Long postId, @Validated PostDto postDto, Model model){
        model.addAttribute("PostDto", PostDto.of(service.updatePost(postId, postDto)));
        return "board/detail";
    }

}
