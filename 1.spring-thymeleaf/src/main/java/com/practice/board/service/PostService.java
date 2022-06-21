package com.practice.board.service;

import com.practice.board.dto.PostDto;
import com.practice.board.entity.Posts;
import com.practice.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;

    public Long register(Posts posts) {
        return repository.save(posts).getId();
    }

    public Posts addPosts(Posts post) {
        return repository.save(post);
    }
}
