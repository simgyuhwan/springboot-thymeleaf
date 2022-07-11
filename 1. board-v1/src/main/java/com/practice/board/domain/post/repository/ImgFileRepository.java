package com.practice.board.domain.post.repository;

import com.practice.board.domain.post.entity.ImgFile;
import com.practice.board.domain.post.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImgFileRepository extends JpaRepository<ImgFile, Long> {
    List<ImgFile> findByPosts(Posts posts);
}
