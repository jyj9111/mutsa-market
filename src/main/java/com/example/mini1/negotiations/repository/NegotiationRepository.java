package com.example.mini1.negotiations.repository;

import com.example.mini1.negotiations.entity.NegotiationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface NegotiationRepository extends JpaRepository<NegotiationEntity, Long> {
    Page<NegotiationEntity> findAllByItemId(Long id, Pageable pageable);
//    Page<NegotiationEntity> findAllByItemIdAndWriterEqualsAndPasswordEquals(Long id, String writer, String Password, Pageable pageable);
    Page<NegotiationEntity> findAllByItemIdAndUserUsername(Long id, String username, Pageable pageable);
    List<NegotiationEntity> findAllByItemId(Long id);
}
