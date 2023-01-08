package com.learning.learningspring.helpers;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ApiResponse {
    private LocalDateTime timestamp;
    private int responseCode;
    private String message;
    private Object data;
}
