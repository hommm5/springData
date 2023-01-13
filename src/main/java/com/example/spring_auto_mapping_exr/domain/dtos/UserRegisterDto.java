package com.example.spring_auto_mapping_exr.domain.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor

public class UserRegisterDto {

    private String email;
    private String password;
    private String fullName;

    @Pattern(regexp = ".+@.+\\..+", message = "Email is not valid")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Size(min = 6, message = "Pass length is not valid.")
    @Pattern(regexp = "[A-Z]+[a-z]+[0-9]+", message = "Password not valid.")
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = "Full name must not be empty")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
