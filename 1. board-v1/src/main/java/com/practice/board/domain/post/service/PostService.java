package com.practice.board.domain.post.service;

import com.practice.board.domain.post.dto.PostDto;
import com.practice.board.domain.post.dto.SearchDto;
import com.practice.board.domain.post.entity.Posts;
import com.practice.board.domain.post.repository.PostRepository;
import com.practice.board.domain.post.repository.PostSearchRepository;
import com.practice.board.domain.post.repository.specification.PostSpecificationBuilder;
import com.practice.board.domain.post.repository.specification.SearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository repository;
    private final PostSearchRepository searchRepository;
    private final ImgFileService imgFileService;

    public Long register(Posts posts) {
        return repository.save(posts).getId();
    }

    public Posts addPosts(PostDto postDto) {
        return repository.save(Posts.of(postDto));
    }

    public PostDto addPosts(PostDto postDto, List<MultipartFile> imgFileList){
        Posts posts = Posts.of(postDto);
        repository.save(posts);

        imgFileService.Register(posts, imgFileList);
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



    // Page V1
    @Transactional(readOnly = true)
    public Page<PostDto> findPage(SearchDto postSearchDto, Pageable pageable){
        Page<PostDto> pages = searchRepository.findAllBySearchDto(postSearchDto, pageable).map(m -> PostDto.of(m));
        return pages;
    }

    // Page V2
    @Transactional(readOnly = true)
    public Page<PostDto> findSearchPage(SearchDto searchDto, Pageable pageable){
        if(!StringUtils.hasText(searchDto.getSearchQuery())){
            return repository.findAll(pageable).map(m -> PostDto.of(m));
        }

        return repository.findAll(PostSpecificationBuilder
                .builder()
                .searchBy(SearchType.valueOf(searchDto.getSearchByToUpper()),
                        searchDto.getSearchQuery())
                .build(), pageable)
                .map(m -> PostDto.of(m));
    }
}
