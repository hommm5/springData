package com.example.spring_auto_mapping_exr.services;

import com.example.spring_auto_mapping_exr.domain.dtos.GameAddDto;
import com.example.spring_auto_mapping_exr.domain.dtos.GameEditDto;

public interface GameService {
    void addGame(GameAddDto gameAddDto);
    void editGame(String [] input);

    void deleteGameById(String s);
    void showAllGames();
}
