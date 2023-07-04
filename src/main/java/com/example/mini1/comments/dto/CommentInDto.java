package com.example.mini1.comments.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentInDto {
    @NotNull
    private String writer;
    @NotNull
    private String password;
    @NotNull
    private String content;
    private String reply;
}
