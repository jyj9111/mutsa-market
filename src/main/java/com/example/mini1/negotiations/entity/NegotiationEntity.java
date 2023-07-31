package com.example.mini1.negotiations.entity;

import com.example.mini1.items.entity.SalesItemEntity;
import com.example.mini1.negotiations.dto.NegoInDto;
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

    @Column(nullable = false)
    private Long suggestedPrice;

    private String status;

    public static NegotiationEntity ofUserItemDto(UserEntity user, SalesItemEntity item, NegoInDto dto) {
        NegotiationEntity newNego = new NegotiationEntity();
        newNego.setUser(user);
        newNego.setItem(item);
        newNego.setSuggestedPrice(dto.getSuggestedPrice());
        newNego.setStatus("제안");
        return newNego;
    }

    public void updateNego(NegoInDto dto) {
        this.suggestedPrice = dto.getSuggestedPrice();
    }

    public void updateStatus(NegoInDto dto) {
        this.status = dto.getStatus();
    }
}
