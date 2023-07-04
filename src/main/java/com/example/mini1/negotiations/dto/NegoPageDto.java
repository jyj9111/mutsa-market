package com.example.mini1.negotiations.dto;

import com.example.mini1.negotiations.entity.NegotiationEntity;
import lombok.Data;

@Data
public class NegoPageDto {
    private Long id;
    private Long suggestedPrice;
    private String status;

    public static NegoPageDto fromEntity(NegotiationEntity entity) {
        NegoPageDto dto = new NegoPageDto();
        dto.setId(entity.getId());
        dto.setSuggestedPrice(entity.getSuggestedPrice());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
