package com.example.spring_auto_mapping_exr.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AllGameViewDto {
    private String title;
    private BigDecimal price;
}
