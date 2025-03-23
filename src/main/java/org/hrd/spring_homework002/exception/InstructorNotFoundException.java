package org.hrd.spring_homework002.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class InstructorNotFoundException extends RuntimeException{
    public InstructorNotFoundException(String message){
        super(message);
    }
}