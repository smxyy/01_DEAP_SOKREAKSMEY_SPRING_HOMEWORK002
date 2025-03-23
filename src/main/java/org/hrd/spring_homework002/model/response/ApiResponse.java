package org.hrd.spring_homework002.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse <T> {
    private String message;
    private T payload;
    private HttpStatus status;
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}
