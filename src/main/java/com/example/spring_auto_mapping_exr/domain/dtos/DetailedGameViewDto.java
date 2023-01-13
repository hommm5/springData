package com.example.spring_auto_mapping_exr.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetailedGameViewDto {
    private String title;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;
}
