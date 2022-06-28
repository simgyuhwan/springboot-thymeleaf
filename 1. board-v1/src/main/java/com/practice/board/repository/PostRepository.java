package com.practice.board.repository;

import com.practice.board.dto.PostDto;
import com.practice.board.dto.PostSearchDto;
import com.practice.board.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface PostRepository extends JpaRepository<Posts, Long> {

    List<Posts> findByTitleContaining(String title);


}
