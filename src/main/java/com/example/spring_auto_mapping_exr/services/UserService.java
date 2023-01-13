package com.example.spring_auto_mapping_exr.services;

import com.example.spring_auto_mapping_exr.domain.dtos.UserLoginDto;
import com.example.spring_auto_mapping_exr.domain.dtos.UserRegisterDto;
import com.example.spring_auto_mapping_exr.domain.entities.User;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);
    void loginUser(UserLoginDto userLoginDto);
    void logout();
    boolean loggedUserIsAdmin();
}
