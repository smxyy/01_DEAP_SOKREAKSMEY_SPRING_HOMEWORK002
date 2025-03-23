package org.hrd.spring_homework002.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.hrd.spring_homework002.model.Course;
import org.hrd.spring_homework002.model.Instructor;
import org.hrd.spring_homework002.model.request.CourseRequest;
import org.hrd.spring_homework002.model.response.ApiResponse;
import org.hrd.spring_homework002.model.response.RemoveResponse;
import org.hrd.spring_homework002.service.CourseService;
import org.hrd.spring_homework002.service.implementation.CourseServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Validated
@RestController
@RequestMapping("api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable("course-id") @Positive @Min(value = 1, message = "Id needs to start from 1") Integer id){
        Course course = courseService.getCourseById(id);
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("The course has been successfully founded.")
                .payload(course)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> updateCourseById(@PathVariable("course-id") @Positive @Min(value = 1, message = "Id needs to start from 1") Integer id, @RequestBody @Valid CourseRequest courseRequest){
        Course course = courseService.updateCourseById(id, courseRequest);
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("The course has been successfully updated.")
                .payload(course)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{course-id}")
    public ResponseEntity<RemoveResponse> removeCourseById(@PathVariable("course-id") @Positive @Min(value = 1, message = "Id needs to start from 1") Integer id){
        courseService.removeCourseById(id);
        RemoveResponse response = RemoveResponse.builder()
                .message("The course has been successfully removed.")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses(@RequestParam(defaultValue = "1") @Positive @Min(value = 1, message = "must be greater than 0") Integer offset, @RequestParam(defaultValue = "10") @Positive @Min(value = 1, message = "must be greater than 0") Integer limit){
        List<Course> courses = courseService.getAllCoursesByPagination(offset, limit);
        ApiResponse<List<Course>> response = ApiResponse.<List<Course>>builder()
                .message("All courses have been successfully fetched.")
                .payload(courses)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> addNewCourse(@RequestBody @Valid CourseRequest courseRequest){
        Course course = courseService.addNewCourse(courseRequest);
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .message("The course has been successfully added.")
                .payload(course)
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
