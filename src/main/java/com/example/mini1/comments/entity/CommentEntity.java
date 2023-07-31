package com.example.mini1.comments.entity;

import com.example.mini1.comments.dto.CommentInDto;
import com.example.mini1.items.entity.SalesItemEntity;
import com.example.mini1.users.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private SalesItemEntity item;

    private String content;
    private String reply;

    public static CommentEntity ofUserItemDto(UserEntity user, SalesItemEntity item, CommentInDto dto) {
        CommentEntity newEntity = new CommentEntity();
        newEntity.setUser(user);
        newEntity.setItem(item);
        newEntity.setContent(dto.getContent());

        return newEntity;
    }

    public void updateComment(CommentInDto dto) {
        this.content = dto.getContent();
    }

    public void setReply(CommentInDto dto) {
        this.reply = dto.getReply();
    }
}
