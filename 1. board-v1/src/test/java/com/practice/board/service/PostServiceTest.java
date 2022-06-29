package com.practice.board.service;

import com.practice.board.domain.post.entity.Posts;
import com.practice.board.domain.post.repository.PostRepository;
import com.practice.board.domain.post.service.PostService;
import com.practice.factory.entity.PostFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @InjectMocks
    PostService service;

    @Mock
    PostRepository repository;

    @Test
    @DisplayName("게시물 등록 테스트")
    void savePostTest(){
        // given
        Posts posts = createPosts();
        given(repository.save(posts)).willReturn(posts);

        // when
        service.register(posts);

        // then
        verify(repository).save(any(Posts.class));
    }

    public List<Posts> createPostList(){
        List<Posts> posts = new ArrayList<>();
        for(int i=0; i<3; i++){
            posts.add(
                    Posts.builder()
                            .title("title" + i)
                            .content("content" + i)
                            .writer("writer" + i)
                            .build()
            );
        }
        return posts;
    }

    public Posts createPosts(){
        return PostFactory.createPosts();
    }

}