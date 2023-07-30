package com.example.mini1.negotiations.exception;

import com.example.mini1.common.exception.status.Status400Exception;

public class UnAuthNegoEditException extends Status400Exception {
    public UnAuthNegoEditException() {
        super("제안 등록자만 수정이 가능합니다.");
    }
}
