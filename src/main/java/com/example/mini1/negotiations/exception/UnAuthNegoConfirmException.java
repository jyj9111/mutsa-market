package com.example.mini1.negotiations.exception;

import com.example.mini1.common.exception.status.Status400Exception;

public class UnAuthNegoConfirmException extends Status400Exception {
    public UnAuthNegoConfirmException() {
        super("제안 등록자만 구매확정이 가능합니다.");
    }
}
