package com.taekwondo.iservice;

import com.taekwondo.dto.user.CreateUserDto;
import com.taekwondo.dto.user.GetUserDto;
import com.taekwondo.entity.User;

public interface IUserService {
    public User create(CreateUserDto userDto);
    public void delete(String id);
}
