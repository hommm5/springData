package com.example.spring_auto_mapping_exr.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game extends BaseEntity{

    private String description;
    private String image;
    private BigDecimal price;
    private LocalDate releaseDate;
    private double size;
    private String title;
    private String trailer;

    @ManyToMany(mappedBy = "games", cascade = CascadeType.ALL)
    private List<User> users;


}
