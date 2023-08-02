package com.example.mini1.items.dto;

import com.example.mini1.comments.dto.CommentOutDto;
import com.example.mini1.comments.entity.CommentEntity;
import com.example.mini1.items.entity.SalesItemEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SalesItemPageDto {
    private Long id;
    private String username;
    private String title;
    private String description;
    private Long minPriceWanted;
    private String imageUrl;
    private String status;
    private List<CommentOutDto> comments;

    public static SalesItemPageDto fromEntity(SalesItemEntity entity) {
        SalesItemPageDto dto = new SalesItemPageDto();

        dto.setId(entity.getId());
        dto.setUsername(entity.getUser().getUsername());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setMinPriceWanted(entity.getMinPriceWanted());
        dto.setImageUrl(entity.getImageUrl());
        dto.setStatus(entity.getStatus());

        List<CommentOutDto> commentList = new ArrayList<>();
        for (CommentEntity temp : entity.getComments()) {
            commentList.add(CommentOutDto.fromEntity(temp));
        }

        dto.setComments(commentList);
        return dto;
    }
}
