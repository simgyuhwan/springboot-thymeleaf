package com.practice.board.config.security.service;

import com.practice.board.config.security.entity.SecurityUser;
import com.practice.board.domain.user.entity.User;
import com.practice.board.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityExistsException;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) {
        User user = userRepository.findByUserId(userId);
        log.info("loadUserByUsername user.userId = {}", userId);
        return new SecurityUser(user);
    }


}
