package com.example.mini1.negotiations.exception;

import com.example.mini1.common.exception.status.Status400Exception;

public class UnAuthNegoDeleteException extends Status400Exception {
    public UnAuthNegoDeleteException() {
        super("제안 등록자만 삭제가 가능합니다.");
    }
}
