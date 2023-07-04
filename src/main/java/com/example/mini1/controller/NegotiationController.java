package com.example.mini1.controller;

import com.example.mini1.dto.ResponseDto;
import com.example.mini1.dto.nego.NegoPageDto;
import com.example.mini1.dto.nego.NegoInDto;
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
            @RequestBody NegoInDto dto
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

    // 제안 수정
    @PutMapping("/proposals/{proposalId}")
    public ResponseDto update(
            @PathVariable("itemId") Long itemId,
            @PathVariable("proposalId") Long propId,
            @RequestBody NegoInDto dto
    ) {
        return service.updateProposal(itemId, propId, dto);
    }

    // 제안 삭제
    @DeleteMapping("/proposals/{proposalId}")
    public ResponseDto delete(
            @PathVariable("itemId") Long itemId,
            @PathVariable("proposalId") Long propId,
            @RequestBody NegoInDto dto
    ) {
        return service.deleteProposal(itemId, propId, dto);
    }
}
