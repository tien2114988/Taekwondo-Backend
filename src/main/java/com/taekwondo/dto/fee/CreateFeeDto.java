package com.taekwondo.dto.fee;

import com.taekwondo.enums.MonthEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateFeeDto {
    @NotNull(message = "Description must not be null")
    private MonthEnum monthEnum;

    @NotNull(message = "Year must not be null")
    private int year;
}

