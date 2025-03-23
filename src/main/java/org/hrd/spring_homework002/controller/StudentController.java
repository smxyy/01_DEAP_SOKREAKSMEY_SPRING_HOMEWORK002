package org.hrd.spring_homework002.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.hrd.spring_homework002.model.Student;
import org.hrd.spring_homework002.model.request.StudentRequest;
import org.hrd.spring_homework002.model.response.ApiResponse;
import org.hrd.spring_homework002.model.response.RemoveResponse;
import org.hrd.spring_homework002.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents(@RequestParam(defaultValue = "1") Integer offset, @RequestParam(defaultValue = "10") Integer limit){
        List<Student> students = studentService.getAllStudents(offset, limit);
        ApiResponse<List<Student>> response = ApiResponse.<List<Student>>builder()
                .message("All student has been successfully fetched.")
                .payload(students)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> createStudent(@RequestBody StudentRequest studentRequest){
        Student student  = studentService.addNewStudent(studentRequest);
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("The student has been successfully created.")
                .payload(student)
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable("student-id") @Positive @Min(value = 1, message = "ID needs to start from 1") Integer id){
        Student student = studentService.getStudentById(id);
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("The student has been successfully founded.")
                .payload(student)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> updateStudentById(@PathVariable("student-id") @Positive @Min(value = 1, message = "ID needs to start from 1") Integer id, @RequestBody StudentRequest studentRequest){
        Student student  = studentService.updateStudentById(id, studentRequest);
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .message("The student has been successfully updated.")
                .payload(student)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{student-id}")
    public ResponseEntity<RemoveResponse> deleteStudentById(@PathVariable("student-id") @Positive @Min(value = 1, message = "ID needs to start from 1") Integer id){
        studentService.removeStudentById(id);
        RemoveResponse response = RemoveResponse.builder()
                .message("The student has been successfully removed.")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
