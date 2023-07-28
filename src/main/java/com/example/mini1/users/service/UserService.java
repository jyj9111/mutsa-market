package com.example.mini1.users.service;

import com.example.mini1.common.dto.ResponseDto;
import com.example.mini1.common.exception.user.ExistUsernameException;
import com.example.mini1.users.dto.UserRegisterDto;
import com.example.mini1.users.entity.CustomUserDetails;
import com.example.mini1.users.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDetailsManager manager;
    private final PasswordEncoder passwordEncoder;


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
}
