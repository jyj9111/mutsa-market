package com.example.mini1.negotiations.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "negotiation")
public class NegotiationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long itemId;

    @Column(nullable = false)
    private Long suggestedPrice;

    private String status;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String password;
}
