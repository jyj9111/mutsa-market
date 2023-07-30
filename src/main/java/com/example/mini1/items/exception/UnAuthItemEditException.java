package com.example.mini1.items.exception;

import com.example.mini1.common.exception.status.Status400Exception;

public class UnAuthItemEditException extends Status400Exception {
    public UnAuthItemEditException() {
        super("상품 등록자만 수정이 가능합니다.");
    }
}
