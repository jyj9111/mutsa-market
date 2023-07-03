package com.example.mini1.dto.comments;

import lombok.Data;

@Data
public class CommentInDto {
    private String writer;
    private String password;
    private String content;
}
