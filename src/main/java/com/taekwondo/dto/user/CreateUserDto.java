package com.taekwondo.dto.user;


import com.taekwondo.annotation.PasswordConstraint;
//import com.taekwondo.annotation.PasswordMatches;

import com.taekwondo.entity.Role;
import com.taekwondo.entity.Student;
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
public class CreateUserDto {
    @NotNull(message = "Username must not be null")
    private String username;

    @NotNull(message = "Password must not be null")
	@PasswordConstraint
    private String password;

    @NotNull(message = "Roles must not be null")
    private Set<Role> roles;

    @NotNull(message = "Student must not be null")
    private Student student;
}
