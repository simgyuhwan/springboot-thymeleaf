package com.practice.board.repository;

import com.practice.board.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Posts, Long> {

    List<Posts> findByTitleContaining(String title);
}
