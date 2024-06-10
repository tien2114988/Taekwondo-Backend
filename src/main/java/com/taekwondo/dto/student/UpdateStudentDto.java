package com.taekwondo.dto.student;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStudentDto {
    @NotNull(message = "Id must not be null")
    private String id;

    private String name;

    private String gender;

    private int yob;

    private String level;

    private String phoneNumber;

    private String status;
}