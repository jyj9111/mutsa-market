package com.example.mini1.controller;

import com.example.mini1.dto.SalesItemOutDto;
import com.example.mini1.service.SalesItemService;
import com.example.mini1.dto.ResponseDto;
import com.example.mini1.dto.SalesItemInDto;
import com.example.mini1.dto.SalesItemPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class SalesItemController {
    private final SalesItemService service;

    @PostMapping
    public ResponseDto create(
            @RequestBody SalesItemInDto dto
            ) {
        ResponseDto response = new ResponseDto();
        service.createSalesItem(dto);
        response.setMessage("등록이 완료되었습니다. ");
        return response;
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
    @GetMapping("{id}")
    public SalesItemOutDto read(@PathVariable("id") Long id) {
        return service.readSalesItem(id);
    }



}
