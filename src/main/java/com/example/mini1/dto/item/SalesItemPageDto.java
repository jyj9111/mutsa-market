package com.example.mini1.dto.item;

import com.example.mini1.entity.SalesItemEntity;
import lombok.Data;

@Data
public class SalesItemPageDto {
    private Long id;
    private String title;
    private String description;
    private Long minPriceWanted;
    private String imageUrl;
    private String status;

    public static SalesItemPageDto fromEntity(SalesItemEntity entity) {
        SalesItemPageDto dto = new SalesItemPageDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setMinPriceWanted(entity.getMinPriceWanted());
        dto.setImageUrl(entity.getImageUrl());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
