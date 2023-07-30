package com.example.mini1.negotiations.controller;

import com.example.mini1.common.dto.ResponseDto;
import com.example.mini1.negotiations.dto.NegoPageDto;
import com.example.mini1.negotiations.dto.NegoInDto;
import com.example.mini1.negotiations.service.NegotiationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/items/{itemId}")
@RequiredArgsConstructor
public class NegotiationController {
    private final NegotiationService service;

    // 구매 제안 등록
    @PostMapping("/proposals")
    public ResponseDto create(
            Principal principal,
            @PathVariable("itemId") Long itemId,
            @RequestBody NegoInDto dto
    ) {
        String username = principal.getName();
        return service.createProposal(username, itemId, dto);
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
    @PutMapping(value = "/proposals/{proposalId}", headers = "mode=buyer")
    public ResponseDto update(
            Principal principal,
            @PathVariable("itemId") Long itemId,
            @PathVariable("proposalId") Long propId,
            @RequestBody NegoInDto dto
    ) {
        String username = principal.getName();
        return service.updateProposal(username, itemId, propId, dto);
    }

    // 제안 수락 Or 거절
    @PutMapping(value = "/proposals/{proposalId}", headers = "mode=seller")
    public ResponseDto updateStatus(
            Principal principal,
            @PathVariable("itemId") Long itemId,
            @PathVariable("proposalId") Long propId,
            @RequestBody NegoInDto dto
    ) {
        String username = principal.getName();
        return service.updateProposalStatus(username, itemId, propId, dto);
    }

    // 구매 확정
    @PutMapping(value = "/proposals/{proposalId}", headers = "mode=end")
    public ResponseDto updateConfirm(
            Principal principal,
            @PathVariable("itemId") Long itemId,
            @PathVariable("proposalId") Long propId,
            @RequestBody NegoInDto dto
    ) {
        String username = principal.getName();
        return service.updateItemAndProposalStatus(username, itemId, propId, dto);
    }

    // 제안 삭제
    @DeleteMapping("/proposals/{proposalId}")
    public ResponseDto delete(
            Principal principal,
            @PathVariable("itemId") Long itemId,
            @PathVariable("proposalId") Long propId,
            @RequestBody NegoInDto dto
    ) {
        String username = principal.getName();
        return service.deleteProposal(username, itemId, propId, dto);
    }
}
