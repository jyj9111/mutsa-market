package com.example.mini1.comments.exception;

import com.example.mini1.common.exception.status.Status400Exception;

public class UnAuthCommentDeleteException extends Status400Exception {
    public UnAuthCommentDeleteException() {
        super("댓글 등록자만 삭제가 가능합니다.");
    }
}
