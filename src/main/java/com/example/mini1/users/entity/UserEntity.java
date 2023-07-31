package com.example.mini1.users.entity;

import com.example.mini1.comments.entity.CommentEntity;
import com.example.mini1.items.entity.SalesItemEntity;
import com.example.mini1.negotiations.entity.NegotiationEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    private String email;
    private String phone;
    private String city;

    @OneToMany(mappedBy = "user")
    private List<SalesItemEntity> items = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<NegotiationEntity> negotiations = new ArrayList<>();
}
