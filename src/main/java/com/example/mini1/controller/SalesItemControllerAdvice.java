package com.example.mini1.controller;

import com.example.mini1.dto.ResponseDto;
import com.example.mini1.dto.SalesItemInDto;
import com.example.mini1.dto.SalesItemOutDto;
import com.example.mini1.dto.SalesItemPageDto;
import com.example.mini1.exception.status.Status400Exception;
import com.example.mini1.exception.status.Status404Exception;
import com.example.mini1.exception.status.Status500Exception;
import com.example.mini1.service.SalesItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestControllerAdvice
public class SalesItemControllerAdvice {

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
