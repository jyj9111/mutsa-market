package com.example.mini1.items.repository;

import com.example.mini1.items.entity.SalesItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesItemRepository extends JpaRepository<SalesItemEntity, Long> {
}
