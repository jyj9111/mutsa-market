package com.example.mini1.negotiations.entity;

import com.example.mini1.items.entity.SalesItemEntity;
import com.example.mini1.users.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "negotiation")
public class NegotiationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private SalesItemEntity item;

/*    @Column(nullable = false)
    private Long itemId;*/

    @Column(nullable = false)
    private Long suggestedPrice;

    private String status;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String password;
}
