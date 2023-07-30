package com.example.mini1.items.controller;

import com.example.mini1.items.dto.SalesItemOutDto;
import com.example.mini1.items.service.SalesItemService;
import com.example.mini1.common.dto.ResponseDto;
import com.example.mini1.items.dto.SalesItemInDto;
import com.example.mini1.items.dto.SalesItemPageDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class SalesItemController {
    private final SalesItemService service;

    // 물품 등록
    @PostMapping
    public ResponseDto create(
            Principal principal,
            @RequestBody SalesItemInDto dto
            ) {
        String username = principal.getName();
        return service.createSalesItem(username, dto);
    }

    // 페이지 전체조회
    @GetMapping
    public Page<SalesItemPageDto> readAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "20") Integer limit
    ) {
        return service.readSalesItemPaged(page, limit);
    }

    // 단일 조회
    @GetMapping("{itemId}")
    public SalesItemOutDto read(@PathVariable("itemId") Long id) {
        return service.readSalesItem(id);
    }

    // 게시글 수정
    @PutMapping("{itemId}")
    public ResponseDto update(
            Principal principal,
            @PathVariable("itemId") Long id,
            @RequestBody SalesItemInDto dto
    ) {
        String username = principal.getName();
        return service.updateSalesItem(username, id, dto);
    }

    // 게시글 삭제
    @DeleteMapping("{itemId}")
    public ResponseDto delete(
            Principal principal,
            @PathVariable("itemId") Long id
    ) {
        String username = principal.getName();
        return service.deleteSalesItem(username, id);
    }

    // 이미지 등록
    @PutMapping(
            value = "/{itemId}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseDto updateItemImage(
            Principal principal,
            @PathVariable("itemId") Long id,
            @RequestParam("image") MultipartFile image
    ) {
        String username = principal.getName();
        return service.updateItemImage(username, id, image);
    }

    @GetMapping("/test")
    public String test(Principal principal) {

        String username = principal.getName();
        log.info(username);
        return "ok";
    }

}
