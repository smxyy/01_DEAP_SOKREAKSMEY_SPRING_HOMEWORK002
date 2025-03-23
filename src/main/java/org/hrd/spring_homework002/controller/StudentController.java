package org.hrd.spring_homework002.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.hrd.spring_homework002.model.Student;
import org.hrd.spring_homework002.model.request.StudentRequest;
import org.hrd.spring_homework002.model.response.ApiResponse;
import org.hrd.spring_homework002.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Student>> getAllStudents(@RequestParam(defaultValue = "1") Integer offset, @RequestParam(defaultValue = "10") Integer limit){
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> createStudent(@RequestBody StudentRequest studentRequest){
        return null;
    }

    @GetMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable("student-id") @Positive @Min(value = 1, message = "ID needs to start from 1") Integer id){
        Student student = studentService.getStudentById(id);
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("The student has been successfully founded")
                .payload(student)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> updateStudentById(@PathVariable("student-id") Integer id, @RequestParam StudentRequest studentRequest){
        return null;
    }

    @DeleteMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> deleteStudentById(@PathVariable("student-id") Integer id){
        return null;
    }
}
