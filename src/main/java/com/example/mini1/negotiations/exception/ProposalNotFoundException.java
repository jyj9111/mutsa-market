package com.example.mini1.negotiations.exception;

import com.example.mini1.common.exception.status.Status404Exception;

public class ProposalNotFoundException extends Status404Exception {
    public ProposalNotFoundException() {
        super("해당 제안이 존재하지 않습니다.");
    }
}
