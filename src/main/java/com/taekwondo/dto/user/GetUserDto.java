package com.taekwondo.dto.user;


//import com.taekwondo.annotation.PasswordMatches;

import com.taekwondo.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

//@PasswordMatches
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserDto {
    private String username;

    private Set<Role> roles;
}
