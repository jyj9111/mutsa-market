package com.example.mini1.users.exception;

import com.example.mini1.common.exception.status.Status400Exception;

public class NotExistUsernameException extends Status400Exception {
    public NotExistUsernameException() {
        super("등록되지 않은 사용자 입니다.");
    }
}
