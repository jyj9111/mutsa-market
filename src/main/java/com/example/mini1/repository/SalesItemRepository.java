package com.example.mini1.repository;

import com.example.mini1.entity.SalesItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesItemRepository extends JpaRepository<SalesItemEntity, Long> {
}
