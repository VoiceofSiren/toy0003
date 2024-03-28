package com.example.springjwt.service;

import com.example.springjwt.dto.CustomedUserDetails;
import com.example.springjwt.entity.User;
import com.example.springjwt.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomedUserDetailsService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    // @RequiredArgsConstructor
    /*
    public CustomedUserDetailsService(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }
    */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = userJpaRepository.findByUsername(username);

        /*
        if (userOptional.isPresent()) {

            // UserDetails에 Entity를 담아서 return하면 AuthenticationManager가 검증함.
            return new CustomedUserDetails(userOptional.get());
        }
        return null;
         */

        return userOptional.map(CustomedUserDetails::new).orElse(null);
    }
}
