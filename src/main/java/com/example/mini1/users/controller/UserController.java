package com.example.mini1.users.controller;


import com.example.mini1.common.dto.ResponseDto;
import com.example.mini1.users.exception.NotMatchedPasswordException;
import com.example.mini1.users.dto.JwtTokenDto;
import com.example.mini1.users.dto.UserLoginDto;
import com.example.mini1.users.dto.UserRegisterDto;
import com.example.mini1.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/register")
    public ResponseDto register(@RequestBody UserRegisterDto dto) {
        if (!dto.getPassword().equals(dto.getPasswordCheck()))
            throw new NotMatchedPasswordException();
        return userService.resgisterUser(dto);
    }

    // 로그인
    @PostMapping("/login")
    public JwtTokenDto login(@RequestBody UserLoginDto dto) {
        return userService.verifyUser(dto);
    }
}
