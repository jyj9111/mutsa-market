package com.example.mini1.users.exception;

import com.example.mini1.common.exception.status.Status400Exception;

public class ExistUsernameException extends Status400Exception {
    public ExistUsernameException() {
        super("이미 존재하는 이름 입니다.");
    }
}
