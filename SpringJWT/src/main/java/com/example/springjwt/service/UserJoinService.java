package com.example.springjwt.service;

import com.example.springjwt.dto.UserJoinDTO;
import com.example.springjwt.entity.User;
import com.example.springjwt.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserJoinService {

    private final UserJpaRepository userJpaRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // @RequiredArgsConstructor
    /*
    public UserJoinService(UserJpaRepository userJpaRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userJpaRepository = userJpaRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
     */

    public void joinProcess(UserJoinDTO userJoinDTO) {
        String username = userJoinDTO.getUsername();
        String password = userJoinDTO.getPassword();

        Boolean userExists = userJpaRepository.existsByUsername(username);

        if (userExists) {

            return;
        }

        User user = new User(username, bCryptPasswordEncoder.encode(password), "ROLE_ADMIN");

        userJpaRepository.save(user);

    }

}
