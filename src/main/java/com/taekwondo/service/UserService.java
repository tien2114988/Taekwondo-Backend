package com.taekwondo.service;

import com.taekwondo.dto.user.GetUserDto;
import com.taekwondo.entity.Role;
import com.taekwondo.enums.StatusCode;
import com.taekwondo.exception.AppException;
import com.taekwondo.iservice.IUserService;
import com.taekwondo.repository.IRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taekwondo.dto.user.CreateUserDto;
import com.taekwondo.entity.User;
import com.taekwondo.repository.IUserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRepo;

    @Autowired
    private IRoleRepository iRoleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create(CreateUserDto createUserDto) {
//        if (emailExists(createUserDto.getEmail())) {
//            throw new AppException(StatusCode.EMAIL_EXISTED);
//        }else
        if(userNameExists(createUserDto.getUsername())) {
            throw new AppException(StatusCode.USERNAME_EXISTED);
		}

        createUserDto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));

        User user = modelMapper.map(createUserDto, User.class);

        return this.iUserRepo.save(user);
    }

    public void delete(String id) {
        iUserRepo.deleteById(id);
    }

    public boolean emailExists(String email) {
        return iUserRepo.findByUsername(email).isPresent();
    }

	public boolean userNameExists(String username) {
		return iUserRepo.findByUsername(username).isPresent();
	}
}
