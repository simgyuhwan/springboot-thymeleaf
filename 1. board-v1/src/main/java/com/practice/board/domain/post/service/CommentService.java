package com.practice.board.domain.post.service;

import com.practice.board.domain.post.dto.CommentRequestDto;
import com.practice.board.domain.post.entity.Comment;
import com.practice.board.domain.post.entity.Posts;
import com.practice.board.domain.post.repository.CommentRepository;
import com.practice.board.domain.post.repository.PostRepository;
import com.practice.board.domain.user.entity.User;
import com.practice.board.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long commentSave(String userId, Long id, CommentRequestDto dto){
        User user = userRepository.findByUserId(userId).orElseThrow(EntityExistsException::new);
        Posts posts = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. " + id));

        dto.setUser(user);
        dto.setPosts(posts);

        Comment comment = dto.toEntity();
        commentRepository.save(comment);

        return dto.getId();
    }
}
