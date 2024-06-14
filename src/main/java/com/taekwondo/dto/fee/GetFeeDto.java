package com.taekwondo.dto.fee;

import com.taekwondo.enums.MonthEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetFeeDto {
    private String id;
    private MonthEnum month;
    private int year;
}

