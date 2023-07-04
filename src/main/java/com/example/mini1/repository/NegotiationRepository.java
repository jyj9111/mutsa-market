package com.example.mini1.repository;

import com.example.mini1.entity.CommentEntity;
import com.example.mini1.entity.NegotiationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NegotiationRepository extends JpaRepository<NegotiationEntity, Long> {
    Page<NegotiationEntity> findAllByItemId(Long id, Pageable pageable);
    Page<NegotiationEntity> findAllByItemIdAndWriterEqualsAndPasswordEquals(Long id, String writer, String Password, Pageable pageable);
    List<NegotiationEntity> findAllByItemId(Long id);
}
