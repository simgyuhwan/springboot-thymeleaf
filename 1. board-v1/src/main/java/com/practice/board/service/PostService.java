package com.practice.board.service;

import com.practice.board.dto.PostDto;
import com.practice.board.dto.PostSearchDto;
import com.practice.board.entity.Posts;
import com.practice.board.repository.PostRepository;
import com.practice.board.repository.PostSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository repository;
    private final PostSearchRepository searchRepository;

    public Long register(Posts posts) {
        return repository.save(posts).getId();
    }

    public Posts addPosts(PostDto postDto) {
        Posts posts = Posts.of(postDto);
        return repository.save(posts);
    }

    @Transactional(readOnly = true)
    public List<PostDto> getPosts() {
           return repository.findAll(Sort.by
                (Sort.Direction.DESC, "createdDate"))
                .stream()
                .map(n -> PostDto.of(n))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Posts getPost(Long postId) {
        return repository.findById(postId)
                .orElseThrow(EntityExistsException::new);
    }

    public Posts updatePost(Long postId, PostDto postDto) {
        Posts posts = repository.findById(postId)
                .orElseThrow(EntityExistsException::new);
        return posts.update(postDto);
    }

    public void deletePost(Long postId) {
        repository.deleteById(postId);
    }

    @Transactional(readOnly = true)
    public Page<PostDto> findPage(PostSearchDto postSearchDto, Pageable pageable){
        Page<PostDto> pages = searchRepository.findAllBySearchDto(postSearchDto, pageable).map(m -> PostDto.of(m));
        //Page<PostDto> pages = repository.findAll(pageable).map(m -> PostDto.of(m));
        return pages;
    }

}
