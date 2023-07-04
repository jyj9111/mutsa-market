package com.example.mini1.common.exception.status;

public class Status500Exception extends RuntimeException{
    public Status500Exception(String message) {
        super(message);
    }
}
