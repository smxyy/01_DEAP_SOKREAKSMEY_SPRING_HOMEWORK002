package org.hrd.spring_homework002.exception;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler1 {

    @ExceptionHandler(InstructorNotFoundException.class)
    public ProblemDetail instructorNotFound(InstructorNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(NOT_FOUND);
        problemDetail.setProperty("message", e.getMessage());
        return problemDetail;
    }
}
