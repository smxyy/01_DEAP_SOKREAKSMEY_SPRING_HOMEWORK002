package org.hrd.spring_homework002.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorRequest {
    @NotBlank(message = "Instructor's name cannot be null!")
    @NotEmpty(message = "Instructor's name cannot be empty!")
    private String instructorName;
    @Email(message = "Invalid instructor's email!")
    @NotBlank(message = "Instructor's email cannot be null!")
    @NotEmpty(message = "Instructor's email cannot be empty!")
    private String email;
}
