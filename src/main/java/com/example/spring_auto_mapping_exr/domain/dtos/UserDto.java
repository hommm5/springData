package com.example.spring_auto_mapping_exr.domain.dtos;

import com.example.spring_auto_mapping_exr.domain.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private long id;
    private String email;
    private String fullName;
    private String password;
    private Role role;

}
