package com.example.mini1.dto.nego;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NegotiationInDto {
    private String writer;
    private String password;
    private Long suggestedPrice;
}
