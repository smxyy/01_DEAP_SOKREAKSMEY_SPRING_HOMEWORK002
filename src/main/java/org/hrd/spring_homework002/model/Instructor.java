package org.hrd.spring_homework002.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Instructor {
//    @JsonProperty (access = JsonProperty.Access.READ_ONLY)
    private Integer instructorId;
    private String instructorName;
    private String email;
}
