package com.example.mini1.comments.dto;

import lombok.Data;

@Data
public class CommentInDto {
    private String writer;
    private String password;
    private String content;
    private String reply;
}
