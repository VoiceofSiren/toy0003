package com.example.springjwt.controller;

import com.example.springjwt.dto.UserJoinDTO;
import com.example.springjwt.service.UserJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserJoinController {

    private final UserJoinService userJoinService;

    // @RequiredArgsConstructor
    /*
    public UserJoinController(UserJoinService userJoinService) {
        this.userJoinService = userJoinService;
    }
    */

    @PostMapping("/join")
    public String joinProcess(UserJoinDTO userJoinDTO) {

        userJoinService.joinProcess(userJoinDTO);

        return "Joined Successfully!";
    }
}
