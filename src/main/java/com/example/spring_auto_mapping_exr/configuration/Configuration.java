package com.example.spring_auto_mapping_exr.configuration;


import com.example.spring_auto_mapping_exr.utils.ValidationUtil;
import com.example.spring_auto_mapping_exr.utils.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }

    @Bean
    public BufferedReader bufferedReader(){
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
