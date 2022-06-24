package com.practice.board.service;

import com.practice.board.dto.PostDto;
import com.practice.board.entity.Posts;
import com.practice.board.repository.PostRepository;
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
    private final PostDtoService postDtoService;

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
    public Page<PostDto> findPage(Pageable pageable){
        List<PostDto> postDtoList = postDtoService.getPostDtoList();
        for (PostDto postDto : postDtoList) {
            System.out.println("===========================================");
            System.out.println("===========================================");
            System.out.println(postDto.getTitle());
            System.out.println(postDto.getContent());
            System.out.println("===========================================");
            System.out.println("===========================================");
            System.out.println("===========================================");
        }
        return new PageImpl<>(postDtoList,
                pageable,
                postDtoList.size());
    }

}
