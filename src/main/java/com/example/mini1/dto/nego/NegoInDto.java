package com.example.mini1.dto.nego;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NegoInDto {
    private String writer;
    private String password;
    private Long suggestedPrice;
    private String status;
}
