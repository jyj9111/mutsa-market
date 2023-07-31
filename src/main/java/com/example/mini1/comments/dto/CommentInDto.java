package com.example.mini1.comments.dto;

import com.example.mini1.comments.entity.CommentEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentInDto {
    @NotNull
    private String content;
    private String reply;
}
