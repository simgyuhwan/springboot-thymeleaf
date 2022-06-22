package com.practice.board.service;

import com.practice.board.dto.PostDto;
import com.practice.board.entity.Posts;
import com.practice.board.mapper.PostMapper;
import com.practice.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;

    public Long register(Posts posts) {
        return repository.save(posts).getId();
    }

    public Posts addPosts(PostDto postDto) {
        Posts posts = PostMapper.MAPPER.toEntity(postDto);
        System.out.println(posts);
        return repository.save(posts);
    }

    public List<PostDto> getPosts() {
           return repository.findAll(Sort.by
                (Sort.Direction.DESC, "createdDate"))
                .stream()
                .map(n -> PostMapper.MAPPER.toDto(n))
                .collect(Collectors.toList());
    }
}
