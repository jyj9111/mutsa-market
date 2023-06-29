package com.example.mini1.dto;

import com.example.mini1.entity.SalesItemEntity;
import lombok.Data;

@Data
public class SalesItemInDto {
    private Long id;
    private String title;
    private String description;
    private Long minPriceWanted;
    private String writer;
    private String status;
    private String password;

    public static SalesItemInDto fromEntity(SalesItemEntity entity) {
        SalesItemInDto dto = new SalesItemInDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setMinPriceWanted(entity.getMinPriceWanted());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
