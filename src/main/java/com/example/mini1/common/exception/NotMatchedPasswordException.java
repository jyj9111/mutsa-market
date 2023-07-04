package com.example.mini1.common.exception;

import com.example.mini1.common.exception.status.Status400Exception;

public class NotMatchedPasswordException extends Status400Exception {
    public NotMatchedPasswordException() {
        super("비밀번호가 일치하지 않습니다.");
    }
}
