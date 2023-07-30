package com.example.mini1.negotiations.exception;

import com.example.mini1.common.exception.status.Status400Exception;

public class UnAuthNegoAcceptException extends Status400Exception {
    public UnAuthNegoAcceptException() {
        super("상품 등록자만 수락 또는 거절이 가능합니다.");
    }
}
