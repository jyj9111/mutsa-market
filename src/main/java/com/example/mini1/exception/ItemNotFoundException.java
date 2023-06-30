package com.example.mini1.exception;

import com.example.mini1.exception.status.Status404Exception;

public class ItemNotFoundException extends Status404Exception {
    public ItemNotFoundException() {
        super("해당 물품이 존재하지 않습니다.");
    }
}
