package com.example.mini1.comments.service;

import com.example.mini1.comments.dto.CommentInDto;
import com.example.mini1.comments.dto.CommentPageDto;
import com.example.mini1.comments.entity.CommentEntity;
import com.example.mini1.comments.repository.CommentRepository;
import com.example.mini1.common.dto.ResponseDto;
import com.example.mini1.items.entity.SalesItemEntity;
import com.example.mini1.common.exception.CommentNotFoundException;
import com.example.mini1.common.exception.ItemNotFoundException;
import com.example.mini1.common.exception.NotMatchedPasswordException;
import com.example.mini1.common.exception.NotMatchedWriterException;
import com.example.mini1.items.repository.SalesItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Page<CommentPageDto> readCommentPaged(Long itemId, Integer page) {
        if(!salesItemRepository.existsById(itemId))
            throw new ItemNotFoundException();

        Pageable pageable = PageRequest.of(page, 25, Sort.by("id"));
        Page<CommentEntity> commentEntityPage = commentRepository.findAllByItemId(itemId, pageable);
        Page<CommentPageDto> commentPageDto = commentEntityPage.map(CommentPageDto::fromEntity);
        return commentPageDto;
    }

    // 댓글 수정
    public ResponseDto updateComment(Long itemId, Long commentId, CommentInDto dto) {
        if(!salesItemRepository.existsById(itemId))
            throw new ItemNotFoundException();

        Optional<CommentEntity> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isEmpty())
            throw new CommentNotFoundException();
        CommentEntity commentEntity = optionalComment.get();
        commentEntity.setWriter(dto.getWriter());
        commentEntity.setPassword(dto.getPassword());
        commentEntity.setContent(dto.getContent());
        commentRepository.save(commentEntity);

        ResponseDto response = new ResponseDto();
        response.setMessage("댓글이 수정되었습니다.");
        return response;
    }

    // 댓글에 대한 답글 추가
    public ResponseDto updateReply(Long itemId, Long commentId, CommentInDto dto) {
        Optional<SalesItemEntity> optionalSalesItem = salesItemRepository.findById(itemId);
        if(optionalSalesItem.isEmpty())
            throw new ItemNotFoundException();

        SalesItemEntity salesItemEntity = optionalSalesItem.get();
        if(!dto.getWriter().equals(salesItemEntity.getWriter()))
            throw new NotMatchedWriterException();
        if(!dto.getPassword().equals(salesItemEntity.getPassword()))
            throw new NotMatchedPasswordException();

        Optional<CommentEntity> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isEmpty())
            throw new CommentNotFoundException();

        CommentEntity commentEntity = optionalComment.get();
        commentEntity.setReply(dto.getReply());
        commentRepository.save(commentEntity);

        ResponseDto response = new ResponseDto();
        response.setMessage("댓글에 답글이 추가되었습니다.");
        return response;
    }

    // 댓글 삭제
    public ResponseDto deleteComment(Long itemId, Long commentId, CommentInDto dto) {
        if(!salesItemRepository.existsById(itemId))
            throw new ItemNotFoundException();

        Optional<CommentEntity> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isEmpty())
            throw new CommentNotFoundException();

        CommentEntity commentEntity = optionalComment.get();
        if(!dto.getWriter().equals(commentEntity.getWriter()))
            throw new NotMatchedWriterException();
        if(!dto.getPassword().equals(commentEntity.getPassword()))
            throw new NotMatchedPasswordException();

        commentRepository.deleteById(commentId);

        ResponseDto response = new ResponseDto();
        response.setMessage("댓글을 삭제했습니다.");
        return response;
    }
}
