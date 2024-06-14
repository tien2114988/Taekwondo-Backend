package com.taekwondo.dto.student;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStudentDto {
    @NotNull(message = "Name must not be null")
    private String name;

    @NotNull(message = "Gender must not be null")
    private String gender;

    @NotNull(message = "Year of birth must not be null")
    private int yob;

    @NotNull(message = "Level must not be null")
    private String level;

    @NotNull(message = "Phone number must not be null")
    private String phoneNumber;

    @NotNull(message = "Status must not be null")
    private String status;

    private Set<String> fees;
}