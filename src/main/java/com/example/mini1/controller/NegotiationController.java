package com.example.mini1.controller;

import com.example.mini1.dto.ResponseDto;
import com.example.mini1.dto.nego.NegoPageDto;
import com.example.mini1.dto.nego.NegotiationInDto;
import com.example.mini1.service.NegotiationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items/{itemId}")
@RequiredArgsConstructor
public class NegotiationController {
    private final NegotiationService service;

    // 구매 제안 등록
    @PostMapping("/proposals")
    public ResponseDto create(
            @PathVariable("itemId") Long itemId,
            @RequestBody NegotiationInDto dto
    ) {
        return service.createProposal(itemId, dto);
    }

    // 제안 조회
    @GetMapping("/proposals")
    public Page<NegoPageDto> readAll(
            @PathVariable("itemId") Long itemId,
            @RequestParam("writer") String writer,
            @RequestParam("password") String password,
            @RequestParam("page") Integer page
    ) {
        return service.readAllProposal(itemId, writer, password, page);
    }

}
