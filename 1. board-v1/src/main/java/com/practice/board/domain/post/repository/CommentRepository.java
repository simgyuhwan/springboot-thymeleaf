package com.practice.board.domain.post.repository;

import com.practice.board.domain.post.entity.Comment;
import com.practice.board.domain.post.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPosts(Posts posts);
}
