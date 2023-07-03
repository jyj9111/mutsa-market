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

    // 댓글 수정
    @PutMapping("/{itemId}/comments/{commentId}")
    public ResponseDto update(
            @PathVariable("itemId") Long itemId,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentInDto dto
    ) {
        return service.updateComment(itemId, commentId, dto);
    }

    // 댓글에 대한 답글 추가
    @PutMapping("/{itemId}/comments/{commentId}/reply")
    public ResponseDto updateReply(
            @PathVariable("itemId") Long itemId,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentInDto dto
    ) {
        return service.updateReply(itemId, commentId, dto);
    }

    // 댓글 삭제
    @DeleteMapping("/{itemId}/comments/{commentId}")
    public ResponseDto delete(
            @PathVariable("itemId") Long itemId,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentInDto dto
    ) {
        return service.deleteComment(itemId, commentId, dto);
    }
}
