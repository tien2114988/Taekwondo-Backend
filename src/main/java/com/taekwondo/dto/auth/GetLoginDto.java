package com.taekwondo.dto.auth;

import com.taekwondo.entity.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetLoginDto {
    @NotNull(message = "Token must not be null")
    private String token;

    @NotNull(message = "Password must not be null")
    private Set<Role> roles;
}