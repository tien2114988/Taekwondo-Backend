package com.taekwondo.dto.role;

import com.taekwondo.entity.Permission;
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
public class GetRoleDto {
    private String id;

    private String description;

    private Set<Permission> permissions;
}