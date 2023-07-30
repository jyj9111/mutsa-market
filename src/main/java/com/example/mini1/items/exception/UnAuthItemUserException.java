package com.example.mini1.items.exception;

import com.example.mini1.common.exception.status.Status401Exception;

public class UnAuthItemUserException extends Status401Exception {
    public UnAuthItemUserException() {
        super("상품 등록자만 수정이 가능합니다.");
    }
}
