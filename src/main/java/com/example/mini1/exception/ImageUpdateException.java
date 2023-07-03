package com.example.mini1.exception;

import com.example.mini1.exception.status.Status500Exception;

public class ImageUpdateException extends Status500Exception {
    public ImageUpdateException() {
        super("이미지 등록과정에서 문제가 발생하였습니다.");
    }
}