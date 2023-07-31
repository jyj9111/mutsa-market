package com.example.mini1.items.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SalesItemInDto {
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    @Min(value = 0, message = "가격은 0원 이상 입력 해주세요")
    private Long minPriceWanted;

}
