package com.example.mini1.users.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String username;
    private String password;
    private String passwordCheck;
    private String email;
    private String phone;
    private String city;
}
