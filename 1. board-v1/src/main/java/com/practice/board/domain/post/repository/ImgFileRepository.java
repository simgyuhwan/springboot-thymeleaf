package com.practice.board.domain.post.repository;

import com.practice.board.domain.post.entity.ImgFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImgFileRepository extends JpaRepository<ImgFile, Long> {
}
