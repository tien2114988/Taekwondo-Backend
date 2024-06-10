package com.taekwondo.config;

import com.taekwondo.entity.Role;
import com.taekwondo.entity.User;
import com.taekwondo.repository.IRoleRepository;
import com.taekwondo.repository.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Configuration
@Slf4j
public class AppConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    @Transactional
    ApplicationRunner applicationRunner(IUserRepository userRepository, IRoleRepository roleRepository){
        return args -> {
            if(roleRepository.findById("ADMIN").isEmpty()){
                Role role = new Role("ADMIN", "Role Manager",new HashSet<>());
                roleRepository.save(role);
            }

            if(roleRepository.findById("USER").isEmpty()){
                Role role = new Role("USER", "Role Student",new HashSet<>());
                roleRepository.save(role);
            }
            
            if(userRepository.findByUsername("admin").isEmpty()){

                List<Role> roles = roleRepository.findAllById(List.of("ADMIN"));
                String password = passwordEncoder.encode("admin");

                Set<Role> roleSet = new HashSet<>(roles);

                User user = User.builder().roles(roleSet).password(password).username("admin").build();
                userRepository.save(user);
            }
        };
    }
}
