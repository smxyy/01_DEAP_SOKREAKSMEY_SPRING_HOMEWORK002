package org.hrd.spring_homework002.model.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    @NotBlank(message = "Student's name cannot be null!")
    @NotEmpty(message = "Student's name cannot be empty!")
    private String studentName;
    @Email(message = "Invalid student's email!")
    private String email;
    @Pattern(regexp = "^0\\d{8,9}$", message = "Invalid student's phone number!")
    private String phoneNumber;
    @Size(min = 1, message = "Course's id cannot be below 0!")
    private List<Integer> coursesId;
}
