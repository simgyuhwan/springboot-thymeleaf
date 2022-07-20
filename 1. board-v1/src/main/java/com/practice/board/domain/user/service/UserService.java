package com.practice.board.domain.user.service;

import com.practice.board.domain.user.dto.SignUpDto;
import com.practice.board.domain.user.entity.Role;
import com.practice.board.domain.user.repository.UserRepository;
import com.practice.board.exception.UserIdAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpDto signUpDto) {
        validateDuplicateUser(signUpDto);
        repository.save(signUpDto.toEntityWithRole(passwordEncoder, Role.USER));
    }

    private void validateDuplicateUser(SignUpDto signUpDto){
        if(repository.existsByUserId(signUpDto.getUserId())){
            throw new UserIdAlreadyExistsException(signUpDto.getUserId());
        }
    }

    public boolean duplicateUserId(String userId) {
        if(repository.existsByUserId(userId)) {
            return true;
        }
        return false;
    }
}
