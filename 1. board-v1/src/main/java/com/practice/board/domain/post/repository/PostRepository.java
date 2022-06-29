package com.practice.board.domain.post.repository;

import com.practice.board.domain.post.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PostRepository extends JpaRepository<Posts, Long> , JpaSpecificationExecutor<Posts> {
    List<Posts> findByTitleContaining(String title);
}
