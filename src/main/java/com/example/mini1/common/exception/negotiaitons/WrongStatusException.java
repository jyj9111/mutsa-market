package com.example.mini1.common.exception.negotiaitons;

import com.example.mini1.common.exception.status.Status400Exception;

public class WrongStatusException extends Status400Exception {
    public WrongStatusException() {
        super("현재 제안이 [수락] 상태가 아닙니다.");
    }
}
