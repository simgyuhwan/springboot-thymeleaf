package com.practice.board.domain.post.service;

import com.practice.board.domain.post.dto.PostDto;
import com.practice.board.domain.post.dto.PostSearchDto;
import com.practice.board.domain.post.entity.Posts;
import com.practice.board.domain.post.repository.PostRepository;
import com.practice.board.domain.post.repository.PostSearchRepository;
import com.practice.board.domain.post.repository.specification.PostSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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


    // 기존 Page 처리
    @Transactional(readOnly = true)
    public Page<PostDto> findPage(PostSearchDto postSearchDto, Pageable pageable){
        Page<PostDto> pages = searchRepository.findAllBySearchDto(postSearchDto, pageable).map(m -> PostDto.of(m));
        return pages;
    }

    public Page<PostDto> findSearchPage(PostSearchDto postSearchDto, Pageable pageable){
        if(!StringUtils.hasText(postSearchDto.getSearchQuery())){
            return repository.findAll(pageable).map(m -> PostDto.of(m));
        }

        return repository.findAll(PostSpecificationBuilder
                .builder()
                .searchBy(postSearchDto.getSearchBy(),
                        postSearchDto.getSearchQuery())
                .build(), pageable)
                .map(m -> PostDto.of(m));
    }


}
