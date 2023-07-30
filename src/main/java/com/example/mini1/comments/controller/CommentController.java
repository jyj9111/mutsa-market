package com.example.mini1.comments.controller;

import com.example.mini1.comments.service.CommentService;
import com.example.mini1.common.dto.ResponseDto;
import com.example.mini1.comments.dto.CommentInDto;
import com.example.mini1.comments.dto.CommentPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items/{itemId}")
public class CommentController {
    private final CommentService service;

    // 댓글 등록
    @PostMapping("/comments")
    public ResponseDto create(
            Principal principal,
            @PathVariable("itemId") Long itemId,
            @RequestBody CommentInDto dto
            ) {
        String username = principal.getName();
        return service.createComment(username, itemId, dto);
    }

    // 댓글 조회 페이지
    @GetMapping("/comments")
    public Page<CommentPageDto> readAll(
            @PathVariable("itemId") Long itemId,
            @RequestHeader("page") Integer page) {
        return service.readCommentPaged(itemId, page);
    }

    // 댓글 수정
    @PutMapping("/comments/{commentId}")
    public ResponseDto update(
            Principal principal,
            @PathVariable("itemId") Long itemId,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentInDto dto
    ) {
        String username = principal.getName();
        return service.updateComment(username, itemId, commentId, dto);
    }

    // 댓글에 대한 답글 추가
    @PutMapping("/comments/{commentId}/reply")
    public ResponseDto updateReply(
            Principal principal,
            @PathVariable("itemId") Long itemId,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentInDto dto
    ) {
        String username = principal.getName();
        return service.updateReply(username, itemId, commentId, dto);
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public ResponseDto delete(
            Principal principal,
            @PathVariable("itemId") Long itemId,
            @PathVariable("commentId") Long commentId
    ) {
        String username = principal.getName();
        return service.deleteComment(username, itemId, commentId);
    }
}
