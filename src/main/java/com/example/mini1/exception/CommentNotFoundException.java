package com.example.mini1.exception;

import com.example.mini1.exception.status.Status404Exception;

public class CommentNotFoundException extends Status404Exception {
    public CommentNotFoundException() {
        super("해당 댓글이 존재하지 않습니다.");
    }
}
