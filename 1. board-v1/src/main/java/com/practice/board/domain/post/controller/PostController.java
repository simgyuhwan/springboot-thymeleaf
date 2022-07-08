package com.practice.board.domain.post.controller;

import com.practice.board.domain.post.dto.PostDto;
import com.practice.board.domain.post.dto.SearchDto;
import com.practice.board.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService service;

    @GetMapping(value = {"", "/{page}"})
    public String list(Model model, @PathVariable("page") Optional<Integer> page, SearchDto postSearchDto){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,
                5, Sort.by(Sort.Direction.DESC,
                        "createdDate"));

        log.info("SearchBy = {}", postSearchDto.getSearchBy());

        model.addAttribute("postSearchDto", new SearchDto());
        model.addAttribute("posts", service.findSearchPage(postSearchDto, pageable));
        model.addAttribute("maxPage", 5);
        return "board/board";
    }

    @GetMapping("/post")
    public String write(Model model){
        model.addAttribute("PostDto",new PostDto());
         return "board/write";
    }

    @PostMapping("/post")
    public String write(HttpServletRequest request, Model model, @Validated PostDto postDto,
                        @RequestPart("imgFile") List<MultipartFile> imgFileList){
        imgFileList.size();
        model.addAttribute("PostDto", PostDto.of(service.addPosts(postDto)));
        return "redirect:/board";
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
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable Long postId){
        service.deletePost(postId);
        return "redirect:/board";
    }

}
