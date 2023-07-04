package com.example.mini1.negotiations.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NegoInDto {
    @NotNull
    private String writer;
    @NotNull
    private String password;
    @NotNull
    @Min(value = 0, message = "가격은 0원 이상 입력 해주세요")
    private Long suggestedPrice;
    @NotNull
    private String status;
}
