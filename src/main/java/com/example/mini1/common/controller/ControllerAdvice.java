package com.example.mini1.common.controller;

import com.example.mini1.common.dto.ResponseDto;
import com.example.mini1.common.exception.status.Status400Exception;
import com.example.mini1.common.exception.status.Status404Exception;
import com.example.mini1.common.exception.status.Status500Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestControllerAdvice
public class ControllerAdvice {

    // 400에러 발생 처리
    @ExceptionHandler(Status400Exception.class)
    public ResponseEntity<ResponseDto> handle400(Status400Exception exception) {
        ResponseDto response = new ResponseDto();
        response.setMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // 404에러 발생 처리
    @ExceptionHandler(Status404Exception.class)
    public ResponseEntity<ResponseDto> handle404(Status404Exception exception) {
        ResponseDto response = new ResponseDto();
        response.setMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // 500에러 발생 처리
    @ExceptionHandler(Status500Exception.class)
    public ResponseEntity<ResponseDto> handle500(Status500Exception exception) {
        ResponseDto response = new ResponseDto();
        response.setMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
