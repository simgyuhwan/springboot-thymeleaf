package com.practice.board.controller;

import com.practice.board.dto.PostDto;
import com.practice.board.entity.Posts;
import com.practice.board.mapper.PostMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PostControllerTest {

    @Test
    public void mapperTest(){
        Posts posts = Posts.builder()
                .writer("writer")
                .title("title")
                .content("content")
                .build();
        System.out.println(posts.getTitle());
        System.out.println(posts.getContent());
        System.out.println(posts.getWriter());

        PostDto postDto = PostMapper.MAPPER.toDto(posts);
        System.out.println(postDto.getTitle());
        System.out.println(postDto.getContent());
        System.out.println(postDto.getWriter());
        assertThat(posts.getTitle()).isEqualTo(postDto.getTitle());
        assertThat(posts.getContent()).isEqualTo(postDto.getContent());
        assertThat(posts.getWriter()).isEqualTo(postDto.getWriter());
    }
}