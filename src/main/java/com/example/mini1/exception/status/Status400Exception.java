package com.example.mini1.exception.status;

public class Status400Exception extends RuntimeException{
    public Status400Exception(String message) {
        super(message);
    }
}
