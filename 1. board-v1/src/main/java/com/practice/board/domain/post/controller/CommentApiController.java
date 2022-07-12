package com.practice.board.domain.post.controller;

import com.practice.board.config.security.entity.SecurityUser;
import com.practice.board.domain.post.dto.CommentRequestDto;
import com.practice.board.domain.post.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity commentSave(@PathVariable Long id, @RequestBody CommentRequestDto dto,
                                      @AuthenticationPrincipal SecurityUser user){
        return ResponseEntity.ok(commentService.commentSave(user.getUserId(), id, dto));
    }
}
