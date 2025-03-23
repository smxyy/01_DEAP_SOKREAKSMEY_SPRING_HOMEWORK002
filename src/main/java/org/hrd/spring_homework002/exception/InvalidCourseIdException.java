package org.hrd.spring_homework002.exception;

public class InvalidCourseIdException extends RuntimeException {
    public InvalidCourseIdException(String message) {
        super(message);
    }
}

