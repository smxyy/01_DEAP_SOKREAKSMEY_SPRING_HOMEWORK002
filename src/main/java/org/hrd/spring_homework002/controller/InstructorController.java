package org.hrd.spring_homework002.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.hrd.spring_homework002.model.Instructor;
import org.hrd.spring_homework002.model.request.InstructorRequest;
import org.hrd.spring_homework002.model.response.ApiResponse;
import org.hrd.spring_homework002.model.response.RemoveResponse;
import org.hrd.spring_homework002.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Validated
@RestController
@RequestMapping("api/vi/instructors")
//@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService){
        this.instructorService = instructorService;
    }

    @GetMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(@PathVariable("instructor-id") @Positive @Min(value = 1, message = "ID needs to start from 1") Integer id){
        Instructor instructor = instructorService.getInstructorById(id);
        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("The instructor has been successfully founded!")
                .payload(instructor)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructorInfoById(@PathVariable("instructor-id") @Positive @Min(value = 1, message = "ID needs to start from 1") Integer id, @RequestBody InstructorRequest instructorRequest){
       Instructor instructor = instructorService.updateInstructorInfoById(id, instructorRequest);
       ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
               .message("The instructor has been successfully updated.")
               .payload(instructor)
               .status(HttpStatus.OK)
               .timestamp(LocalDateTime.now())
               .build();
       return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{instructor-id}")
    public ResponseEntity<RemoveResponse> removeInstructorById(@PathVariable("instructor-id") @Positive @Min(value = 1, message = "ID needs to start from 1") Integer id){
        instructorService.removeInstructorById(id);
        RemoveResponse response = RemoveResponse.builder()
                .message("The instructor has been successfully removed.")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    @Operation ( summary = "Get all instructors")
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructor(@RequestParam(defaultValue = "1") Integer offset, @RequestParam(defaultValue = "10") Integer limit){
        List<Instructor> instructors = instructorService.getAllInstructorsByPagination(offset, limit);
        ApiResponse<List<Instructor>> response = ApiResponse.<List<Instructor>>builder()
                .message("All instructors have been successfully fetched.")
                .payload(instructors)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> createInstructor(@RequestBody @Valid InstructorRequest instructorRequest ){
        Instructor instructor = instructorService.createInstructor(instructorRequest);
        ApiResponse<Instructor> response = ApiResponse.<Instructor>builder()
                .message("The instructor has been successfully added!")
                .payload(instructor)
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
