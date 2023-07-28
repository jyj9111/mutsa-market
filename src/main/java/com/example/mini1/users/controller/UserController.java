package com.example.mini1.users.controller;


import com.example.mini1.common.dto.ResponseDto;
import com.example.mini1.common.exception.NotMatchedPasswordException;
import com.example.mini1.users.dto.UserRegisterDto;
import com.example.mini1.users.entity.CustomUserDetails;
import com.example.mini1.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDetailsManager manager;

    // 회원가입
    @PostMapping("/register")
    public ResponseDto register(@RequestBody UserRegisterDto dto) {
        if (!dto.getPassword().equals(dto.getPasswordCheck()))
            throw new NotMatchedPasswordException();
        return userService.resgisterUser(dto);
    }
}
