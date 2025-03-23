package org.hrd.spring_homework002.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    @NotBlank(message = "Course's name cannot be null!")
    @NotEmpty(message = "Course's name cannot be empty!")
    private String courseName;
    @NotBlank(message = "Course's description cannot be null!")
    @NotEmpty(message = "Course's description cannot be empty!")
    private String description;
    @Size(min = 1, message = "Instructor's id cannot be below 0!")
    @NotBlank(message = "Instructor's id cannot be null!")
    private Integer instructorId;
}
