package com.example.spring_auto_mapping_exr.services.impl;

import com.example.spring_auto_mapping_exr.domain.dtos.UserDto;
import com.example.spring_auto_mapping_exr.domain.dtos.UserLoginDto;
import com.example.spring_auto_mapping_exr.domain.dtos.UserRegisterDto;
import com.example.spring_auto_mapping_exr.domain.entities.Role;
import com.example.spring_auto_mapping_exr.domain.entities.User;
import com.example.spring_auto_mapping_exr.repositories.UserRepository;
import com.example.spring_auto_mapping_exr.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private UserDto userDto;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        User user = modelMapper.map(userRegisterDto, User.class);
        user.setRole(userRepository.count() == 0 ? Role.ADMIN : Role.USER);
        this.userRepository.save(user);
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        User user = userRepository.findUserByEmail(userLoginDto.getEmail());

        if (user == null) {
            System.out.println("Incorrect username / password");
        } else {
            userDto = modelMapper.map(user, UserDto.class);
            System.out.printf("Successfully logged in %s%n", userDto.getFullName());

        }
    }

    @Override
    public void logout() {
        System.out.println();
        if (userDto == null) {
            System.out.println("Cannot log out. No user was logged in.");

        } else {

            String name = userDto.getFullName();
            System.out.printf("User %s successfully logged out %n", name);
            userDto = null;
        }

    }

    @Override
    public boolean loggedUserIsAdmin() {
        boolean result = false;
        try {
            result = userDto.getRole().equals(Role.ADMIN);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


}
