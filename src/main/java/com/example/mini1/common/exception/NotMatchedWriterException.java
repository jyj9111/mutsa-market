package com.example.mini1.common.exception;

import com.example.mini1.common.exception.status.Status400Exception;

public class NotMatchedWriterException extends Status400Exception {
    public NotMatchedWriterException() {
        super("작성자가 일치하지 않습니다.");
    }
}
