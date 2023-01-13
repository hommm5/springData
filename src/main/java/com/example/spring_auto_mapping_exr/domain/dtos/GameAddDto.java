package com.example.spring_auto_mapping_exr.domain.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
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
public class GameAddDto {

    @Size(min = 3, max = 100, message = "Title is not valid")
    @Pattern(regexp = "^[A-Z].+", message = "Title is not valid")
    private String title;
    @DecimalMin(value = "0", message = "Price must be a positive number")
    private BigDecimal price;
    @Min(value = (long) 0, message = "Size must be a positive number")
    private double size;
    @Size(min = 11, max = 11, message = "Trailer is not valid")
    private String trailer;
    @Pattern(regexp = "^http:\\/\\/.+|https:\\/\\/.+", message = "Image not valid")
    private String image;
    @Size(min = 20, message = "Description is not valid")
    private String description;
    private LocalDate releaseDate;







}
