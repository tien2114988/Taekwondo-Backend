package com.taekwondo.dto.student;

import com.taekwondo.entity.Fee;
import com.taekwondo.entity.FeePayment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStudentDto {
    private String id;
    private String name;
    private String gender;
    private int yob;
    private String level;
    private String phoneNumber;
    private String status;
    private LocalDateTime createdDate;
    private Set<FeePayment> fees;
}
