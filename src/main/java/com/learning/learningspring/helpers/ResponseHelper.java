package com.learning.learningspring.helpers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHelper {

    private ResponseHelper() {
    }

    public static ResponseEntity<Object> build(HttpStatus httpStatus, String message, Object data) {

        ApiResponse apiResponse = ApiResponse.builder()
                .timestamp(LocalDateTime.now(ZoneId.of("GMT+7")))
                .responseCode(httpStatus.value())
                .message(message)
                .data(data)
                .build();

        return new ResponseEntity<>(apiResponse, httpStatus);
    }
}
