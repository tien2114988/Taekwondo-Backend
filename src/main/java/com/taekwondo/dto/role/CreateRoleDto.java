package com.taekwondo.dto.role;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRoleDto {
    @NotNull(message = "Id must not be null")
    private String id;

    @NotNull(message = "Description must not be null")
    private String description;

    @NotNull(message = "Permissions must not be null")
    private Set<String> permissions;
}