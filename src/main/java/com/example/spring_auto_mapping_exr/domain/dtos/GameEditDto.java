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
public class GameEditDto {
    private long id;
    private String description;
    private String image;
    private BigDecimal price;
    private LocalDate releaseDate;
    private double size;
    private String title;
    private String trailer;
}
