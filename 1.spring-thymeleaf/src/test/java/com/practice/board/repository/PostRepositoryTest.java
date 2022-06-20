package com.practice.board.repository;

import com.practice.board.entity.Posts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository repository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("게시물 등록 테스트")
    void savePostTest() {
        Posts posts = createPost();
        Posts savePosts = repository.save(posts);
        assertEquals(posts.getId(), savePosts.getId());
    }
    @Test
    @DisplayName("게시물 삭제 테스트")
    void deletePostTest(){
        Posts savePost = repository.save(createPost());
        repository.delete(savePost);
        assertThat(repository.existsById(savePost.getId())).isFalse();
    }

    @Test
    @DisplayName("게시물 Id 조회 테스트")
    void searchByIdPostTest(){
        Posts savePost = repository.save(createPost());
        Posts findPost = repository.findById(savePost.getId()).orElseThrow(EntityExistsException::new);
        assertThat(savePost.getId()).isEqualTo(findPost.getId());
    }

    @Test
    @DisplayName("생성 시간 테스트")
    void createdDateTest(){
        Posts savePost = repository.save(createPost());
        assertThat(savePost.getCreatedDate()
                .format(DateTimeFormatter.ofPattern("yyyy MM dd")))
                .isEqualTo(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy MM dd")));
    }

    @Test
    @DisplayName("제목 조회 Like 테스트")
    void findByTitleInPosts(){
        Posts savePosts = repository.save(new Posts("test1", "content", "writer"));
        clear();
        List<Posts> posts = repository.findByTitleContaining("tes");
        assertThat(savePosts.getTitle())
                .isEqualTo(posts.get(0).getTitle());
    }

    private Posts createPost() {
        return new Posts("test", "content test", "writer");
    }

    public void clear(){
        em.flush();
        em.clear();
    }

}