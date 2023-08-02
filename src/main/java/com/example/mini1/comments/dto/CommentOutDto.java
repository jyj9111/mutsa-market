package com.example.mini1.comments.dto;

import com.example.mini1.comments.entity.CommentEntity;
import lombok.Data;

@Data
public class CommentOutDto {
    private Long id;
    private String username;
    private String content;
    private String reply;

    public static CommentOutDto fromEntity(CommentEntity entity) {
        CommentOutDto dto = new CommentOutDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUser().getUsername());
        dto.setContent(entity.getContent());
        dto.setReply(entity.getReply());
        return dto;
    }
}
