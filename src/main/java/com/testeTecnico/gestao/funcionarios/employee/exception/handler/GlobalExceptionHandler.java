package com.testeTecnico.gestao.funcionarios.employee.exception.handler;


import com.testeTecnico.gestao.funcionarios.employee.exception.details.*;
import com.testeTecnico.gestao.funcionarios.employee.exception.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BadRequestDetails> handleHttpMessageNotReadableException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                BadRequestDetails.builder()
                        .title("Bad Request check the documentation")
                        .details(ex.getMessage() + ", check the documentation on: localhost:8080/api/public/swagger-ui/index.html")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .developerMessage(ex.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BadRequestDetails> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                BadRequestDetails.builder()
                        .title("Syntax error")
                        .details(ex.getMessage() + ", check the documentation on: localhost:8080/api/public/swagger-ui/index.html")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .developerMessage(ex.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<MethodNotAllowedDetails> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(
                MethodNotAllowedDetails.builder()
                        .title("Method not allowed")
                        .details(ex.getMessage() + ", check the documentation on: localhost:8080/api/public/swagger-ui/index.html")
                        .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                        .developerMessage(ex.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<BadRequestDetails> handleNullPointerException(NullPointerException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                BadRequestDetails.builder()
                        .title("Bad Request check the documentation")
                        .details("Bad Request, check the documentation")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .developerMessage(ex.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundDetails> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                NotFoundDetails.builder()
                        .title("Content not found, verify the id")
                        .details(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .developerMessage(ex.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationDetails> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).reduce("", (a, b) -> a + ", " + b);
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).reduce("", (a, b) -> a + ", " + b);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ValidationDetails.builder()
                        .title("Bad Request Exception, invalid fields")
                        .details("Check the fields error")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .developerMessage(ex.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .field(fields)
                        .fieldsMessage(fieldsMessage)
                        .build()
        );
    }



}