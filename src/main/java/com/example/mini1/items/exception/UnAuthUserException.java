package com.example.mini1.items.exception;

import com.example.mini1.common.exception.status.Status401Exception;

public class UnAuthUserException extends Status401Exception {
    public UnAuthUserException() {
        super("상품 등록자만 수정이 가능합니다.");
    }
}
