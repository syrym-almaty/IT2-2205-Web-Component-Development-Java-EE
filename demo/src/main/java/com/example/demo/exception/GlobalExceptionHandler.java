package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

// Аннотация для глобального обработчика исключений
@ControllerAdvice
public class GlobalExceptionHandler {

    // Обработка исключений ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(), // Код состояния
                ex.getMessage(), // Сообщение об ошибке
                LocalDateTime.now() // Время возникновения ошибки
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // Возвращаем ответ с ошибкой и статусом 404
    }

    // Вы можете добавить больше обработчиков исключений здесь

    // Внутний класс для формата ответа об ошибке
    public static class ErrorResponse {
        private int status; // Код состояния
        private String message; // Сообщение об ошибке
        private LocalDateTime timestamp; // Время возникновения ошибки

        public ErrorResponse(int status, String message, LocalDateTime timestamp) {
            this.status = status;
            this.message = message;
            this.timestamp = timestamp;
        }

        // Геттеры и сеттеры
        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }
    }
}
