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

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 userId 입니다."));
        log.info("loadUserByUsername user.userId = {}", userId);
        return new SecurityUser(user);
    }


}
