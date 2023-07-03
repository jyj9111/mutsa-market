package com.example.mini1.controller;

import com.example.mini1.dto.ResponseDto;
import com.example.mini1.dto.comments.CommentInDto;
import com.example.mini1.dto.comments.CommentPageDto;
import com.example.mini1.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class CommentController {
    private final CommentService service;

    // 댓글 등록
    @PostMapping("/{itemId}/comments")
    public ResponseDto create(
            @PathVariable("itemId") Long itemId,
            @RequestBody CommentInDto dto
            ) {
        return service.createComment(itemId, dto);
    }

    // 댓글 조회 페이지
    @GetMapping("/{itemId}/comments")
    public Page<CommentPageDto> readAll(@PathVariable("itemId") Long itemId) {
        return service.readCommentPaged(itemId);
    }
}
