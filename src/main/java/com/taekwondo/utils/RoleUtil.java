package com.taekwondo.utils;

import com.taekwondo.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.StringJoiner;

@Component
public class RoleUtil {

    @Bean
    public String buildScope(Set<Role> roles){
        StringJoiner sj = new StringJoiner(" ");
        if(roles != null && !roles.isEmpty()){
            roles.forEach(role -> {
                sj.add("ROLE_".concat(role.getId()));
                role.getPermissions().forEach(permission -> sj.add(permission.getId()));
            });
        }
        return sj.toString();
    }

}
