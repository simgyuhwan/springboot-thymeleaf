package com.practice.board.controller;

import com.practice.board.dto.PostDto;
import com.practice.board.entity.Posts;
import com.practice.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class PostController {
    private final PostService service;

    @GetMapping(value = {"", "/{page}"})
    public String list(Model model, @PathVariable("page") Optional<Integer> page ){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
        model.addAttribute("posts", service.findPage(pageable));
        model.addAttribute("maxPage", 5);
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

    @GetMapping("/post/detail/{postId}")
    public String read(@PathVariable Long postId, Model model){
        model.addAttribute("PostDto", PostDto.of(service.getPost(postId)));
        return "board/detail";
    }

    @GetMapping("/post/{postId}")
    public String update(@PathVariable Long postId, Model model){
        model.addAttribute("PostDto", PostDto.of(service.getPost(postId)));
        return "board/update";
    }

    @PostMapping("/post/{postId}")
    public String update(@PathVariable Long postId, @Validated PostDto postDto, Model model){
        model.addAttribute("PostDto", PostDto.of(service.updatePost(postId, postDto)));
        return "board/detail";
    }

    @DeleteMapping("/post/detail/{postId}")
    public String delete(@PathVariable Long postId){
        service.deletePost(postId);
        return "redirect:/board";
    }



}
