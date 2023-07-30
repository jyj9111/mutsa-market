package com.example.mini1.items.entity;

import com.example.mini1.comments.entity.CommentEntity;
import com.example.mini1.negotiations.entity.NegotiationEntity;
import com.example.mini1.users.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "sales_item")
public class SalesItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    private String imageUrl;

    @Column(nullable = false)
    private Long minPriceWanted;

    private String status;

    @OneToMany
    @JoinColumn(name = "item_id")
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "item_id")
    private List<NegotiationEntity> negotiations = new ArrayList<>();

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String password;
}
