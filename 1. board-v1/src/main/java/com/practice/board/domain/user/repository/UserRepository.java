package com.practice.board.domain.user.repository;

import com.practice.board.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserId(String user_id);
    User findByUserId(String userId);
}
