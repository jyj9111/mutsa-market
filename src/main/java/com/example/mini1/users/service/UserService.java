package com.example.mini1.users.service;

import com.example.mini1.common.dto.ResponseDto;
import com.example.mini1.users.exception.NotMatchedPasswordException;
import com.example.mini1.users.exception.ExistUsernameException;
import com.example.mini1.users.exception.NotExistUsernameException;
import com.example.mini1.security.jwt.JwtTokenUtils;
import com.example.mini1.users.dto.JwtTokenDto;
import com.example.mini1.users.dto.UserLoginDto;
import com.example.mini1.users.dto.UserRegisterDto;
import com.example.mini1.users.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDetailsManager manager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;


    public ResponseDto resgisterUser(UserRegisterDto dto) {
        if (manager.userExists(dto.getUsername()))
            throw new ExistUsernameException();

        manager.createUser(CustomUserDetails.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .city(dto.getCity())
                .build());

        ResponseDto response = new ResponseDto();
        response.setMessage("회원가입이 완료되었습니다.");
        return response;
    }

    public JwtTokenDto verifyUser(UserLoginDto dto) {
        if (!manager.userExists(dto.getUsername()))
            throw new NotExistUsernameException();

        UserDetails userDetails = manager.loadUserByUsername(dto.getUsername());

        if (!passwordEncoder.matches(dto.getPassword(), userDetails.getPassword()))
            throw new NotMatchedPasswordException();

        JwtTokenDto jwtResponse = new JwtTokenDto();
        jwtResponse.setToken(jwtTokenUtils.generateToken(userDetails));
        return jwtResponse;
    }
}
