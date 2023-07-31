package com.example.mini1.items.entity;

import com.example.mini1.comments.entity.CommentEntity;
import com.example.mini1.items.dto.SalesItemInDto;
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

    @OneToMany(mappedBy = "item")
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<NegotiationEntity> negotiations = new ArrayList<>();


    public static SalesItemEntity fromDto(SalesItemInDto dto) {
        SalesItemEntity newEntity = new SalesItemEntity();
        newEntity.setTitle(dto.getTitle());
        newEntity.setDescription(dto.getDescription());
        newEntity.setMinPriceWanted(dto.getMinPriceWanted());
        newEntity.setStatus("판매중");

        return newEntity;
    }

    public void updateItem(SalesItemInDto dto) {
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.minPriceWanted = dto.getMinPriceWanted();
    }
}
