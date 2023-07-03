package com.example.mini1.service;

import com.example.mini1.dto.ResponseDto;
import com.example.mini1.dto.comments.CommentInDto;
import com.example.mini1.dto.comments.CommentPageDto;
import com.example.mini1.entity.CommentEntity;
import com.example.mini1.exception.ItemNotFoundException;
import com.example.mini1.repository.CommentRepository;
import com.example.mini1.repository.SalesItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final SalesItemRepository salesItemRepository;

    // 댓글 등록
    public ResponseDto createComment(Long itemId, CommentInDto dto) {
        if(!salesItemRepository.existsById(itemId))
            throw new ItemNotFoundException();

        CommentEntity newEntity = new CommentEntity();
        newEntity.setItemId(itemId);
        newEntity.setWriter(dto.getWriter());
        newEntity.setPassword(dto.getPassword());
        newEntity.setContent(dto.getContent());
        commentRepository.save(newEntity);

        ResponseDto response = new ResponseDto();
        response.setMessage("댓글이 등록되었습니다.");
        return response;
    }

    // 페이지 단위 해당 하이템 댓글 조회
    public Page<CommentPageDto> readCommentPaged(Long itemId) {
        if(!salesItemRepository.existsById(itemId))
            throw new ItemNotFoundException();

        Pageable pageable = PageRequest.of(0, 25, Sort.by("id"));
        Page<CommentEntity> commentEntityPage = commentRepository.findAllByItemId(itemId, pageable);
        Page<CommentPageDto> commentPageDto = commentEntityPage.map(CommentPageDto::fromEntity);
        return commentPageDto;
    }
}
