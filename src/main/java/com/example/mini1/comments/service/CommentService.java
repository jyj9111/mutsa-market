package com.example.mini1.comments.service;

import com.example.mini1.comments.dto.CommentInDto;
import com.example.mini1.comments.dto.CommentPageDto;
import com.example.mini1.comments.entity.CommentEntity;
import com.example.mini1.comments.exception.UnAuthCommentDeleteException;
import com.example.mini1.comments.exception.UnAuthCommentEditException;
import com.example.mini1.comments.exception.UnAuthCommentReplyException;
import com.example.mini1.comments.repository.CommentRepository;
import com.example.mini1.common.dto.ResponseDto;
import com.example.mini1.users.exception.NotExistUsernameException;
import com.example.mini1.items.entity.SalesItemEntity;
import com.example.mini1.comments.exception.CommentNotFoundException;
import com.example.mini1.items.exception.ItemNotFoundException;
import com.example.mini1.items.repository.SalesItemRepository;
import com.example.mini1.users.entity.UserEntity;
import com.example.mini1.users.repo.UserRepository;
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
    private final UserRepository userRepository;

    // 댓글 등록
    public ResponseDto createComment(String username, Long itemId, CommentInDto dto) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty())
            throw new NotExistUsernameException();
        UserEntity user = optionalUser.get();

        Optional<SalesItemEntity> optionalItem = salesItemRepository.findById(itemId);
        if (optionalItem.isEmpty())
            throw new ItemNotFoundException();
        SalesItemEntity item = optionalItem.get();

        CommentEntity newEntity = CommentEntity.ofUserItemDto(user, item, dto);
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
    public ResponseDto updateComment(String username, Long itemId, Long commentId, CommentInDto dto) {

        if(!salesItemRepository.existsById(itemId))
            throw new ItemNotFoundException();

        Optional<CommentEntity> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isEmpty())
            throw new CommentNotFoundException();
        CommentEntity commentEntity = optionalComment.get();

        if (!commentEntity.getUser().getUsername().equals(username))
            throw new UnAuthCommentEditException();

        commentEntity.updateComment(dto);
        commentRepository.save(commentEntity);

        ResponseDto response = new ResponseDto();
        response.setMessage("댓글이 수정되었습니다.");
        return response;
    }

    // 댓글에 대한 답글 추가
    public ResponseDto updateReply(String username, Long itemId, Long commentId, CommentInDto dto) {
        Optional<SalesItemEntity> optionalSalesItem = salesItemRepository.findById(itemId);
        if(optionalSalesItem.isEmpty())
            throw new ItemNotFoundException();

        SalesItemEntity itemEntity = optionalSalesItem.get();
        if (!itemEntity.getUser().getUsername().equals(username))
            throw new UnAuthCommentReplyException();

        Optional<CommentEntity> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isEmpty())
            throw new CommentNotFoundException();

        CommentEntity commentEntity = optionalComment.get();
        commentEntity.setReply(dto);
        commentRepository.save(commentEntity);

        ResponseDto response = new ResponseDto();
        response.setMessage("댓글에 답글이 추가되었습니다.");
        return response;
    }

    // 댓글 삭제
    public ResponseDto deleteComment(String username, Long itemId, Long commentId) {
        if(!salesItemRepository.existsById(itemId))
            throw new ItemNotFoundException();

        Optional<CommentEntity> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isEmpty())
            throw new CommentNotFoundException();

        CommentEntity commentEntity = optionalComment.get();
        if (!commentEntity.getUser().getUsername().equals(username))
            throw new UnAuthCommentDeleteException();

        commentRepository.deleteById(commentId);

        ResponseDto response = new ResponseDto();
        response.setMessage("댓글을 삭제했습니다.");
        return response;
    }
}
