package com.example.mini1.items.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sales_item")
public class SalesItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String imageUrl;
    private Long minPriceWanted;
    private String status;
    private String writer;
    private String password;
}
