package com.example.Project.exception;

import com.example.Project.dto.response.ApiResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse<Void>> handleRuntimeException(RuntimeException exception) {
        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();

        return  ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage) // Lấy ra thông báo của các lỗi trong validation
                .collect(Collectors.toList());

        String errorMessage = String.join(", ", errors); // Tạo một thông báo chung từ danh sách lỗi

        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(errorMessage)
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    ResponseEntity<ApiResponse<Void>> handleNoSuchElementException(NoSuchElementException exception) {
        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .build();

        return  ResponseEntity.badRequest().body(apiResponse);
    }

}