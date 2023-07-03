package com.example.mini1.dto.comments;

import com.example.mini1.entity.CommentEntity;
import lombok.Data;

@Data
public class CommentPageDto {
    private Long id;
    private String content;
    private String reply;

    public static CommentPageDto fromEntity(CommentEntity entity) {
        CommentPageDto dto = new CommentPageDto();
        dto.setId(entity.getItemId());
        dto.setContent(entity.getContent());
        dto.setReply(entity.getReply());
        return dto;
    }
}
