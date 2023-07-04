package com.example.mini1.items.controller;

import com.example.mini1.items.dto.SalesItemOutDto;
import com.example.mini1.items.service.SalesItemService;
import com.example.mini1.common.dto.ResponseDto;
import com.example.mini1.items.dto.SalesItemInDto;
import com.example.mini1.items.dto.SalesItemPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class SalesItemController {
    private final SalesItemService service;

    @PostMapping
    public ResponseDto create(
            @RequestBody SalesItemInDto dto
            ) {
        return service.createSalesItem(dto);
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
            @PathVariable("itemId") Long id,
            @RequestBody SalesItemInDto dto
    ) {
        return service.updateSalesItem(id, dto);
    }

    // 게시글 삭제
    @DeleteMapping("{itemId}")
    public ResponseDto delete(
            @PathVariable("itemId") Long id,
            @RequestBody SalesItemInDto dto
    ) {
        return service.deleteSalesItem(id, dto);
    }

    // 이미지 등록
    @PutMapping(
            value = "/{itemId}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseDto updateItemImage(
            @PathVariable("itemId") Long id,
            @RequestParam("image") MultipartFile image,
            @RequestParam("writer") String writer,
            @RequestParam("password") String password
    ) {
        return service.updateItemImage(id, image, writer, password);
    }

}
