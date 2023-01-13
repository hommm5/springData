package com.example.spring_auto_mapping_exr.services.impl;

import com.example.spring_auto_mapping_exr.domain.dtos.AllGameViewDto;
import com.example.spring_auto_mapping_exr.domain.dtos.GameAddDto;
import com.example.spring_auto_mapping_exr.domain.dtos.GameEditDto;
import com.example.spring_auto_mapping_exr.domain.entities.Game;
import com.example.spring_auto_mapping_exr.repositories.GameRepository;
import com.example.spring_auto_mapping_exr.services.GameService;
import com.example.spring_auto_mapping_exr.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;



@Service
public class GameServiceImpl implements GameService {

    private final ModelMapper modelMapper;
    private final GameRepository gameRepository;
    private final UserService userService;


    @Autowired
    public GameServiceImpl(ModelMapper modelMapper, GameRepository gameRepository, UserService userService) {
        this.modelMapper = modelMapper;
        this.gameRepository = gameRepository;
        this.userService = userService;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {

        if (!userService.loggedUserIsAdmin()) {
            System.out.println("User is not admin");
            return;
        }

        Game game = modelMapper.map(gameAddDto, Game.class);

        gameRepository.save(game);

        System.out.printf("Added %s%n", gameAddDto.getTitle());
    }

    @Override
    public void editGame(String[] input) {

        Game gameFromRepository = new Game();

        try {
            gameFromRepository = gameRepository.findGameById(Long.parseLong(input[1]))
                    .orElseThrow(IllegalAccessError::new);
        } catch (IllegalAccessError e) {
            System.out.println("No such id");
        }


        if (!userService.loggedUserIsAdmin()) {
            System.out.println("User is not admin");
            return;
        }


        GameEditDto gameEditDto = modelMapper.map(gameFromRepository, GameEditDto.class);


        for (int i = 2; i < input.length; i++) {
            String[] split = input[i].split("=");
            String fieldName = split[0];
            String parameter = split[1];

            switch (fieldName) {
                case "description" -> gameEditDto.setDescription(parameter);
                case "image" -> gameEditDto.setImage(parameter);
                case "price" -> gameEditDto.setPrice(new BigDecimal(parameter));
                case "releaseDate" -> gameEditDto.setReleaseDate(LocalDate.parse(parameter));
                case "size" -> gameEditDto.setSize(Double.parseDouble(parameter));
                case "title" -> gameEditDto.setTitle(parameter);
                case "trailer" -> gameEditDto.setTrailer(parameter);
            }

        }

        Game updatedGame = modelMapper.map(gameEditDto, Game.class);
        updatedGame.setId(Long.parseLong(input[1]));
        gameRepository.save(updatedGame);
        System.out.printf("Edited %s", gameEditDto.getTitle());
    }

    @Override
    public void deleteGameById(String s) {
        Game gameFromRepository = new Game();

        try {
            gameFromRepository = gameRepository.findGameById(Long.parseLong(s))
                    .orElseThrow(IllegalAccessError::new);
        } catch (IllegalAccessError e) {
            System.out.println("No such id");
        }
        if (!userService.loggedUserIsAdmin()) {
            System.out.println("User is not admin");
            return;
        }

        GameEditDto gameEditDto = modelMapper.map(gameFromRepository, GameEditDto.class);

        gameRepository.deleteGameById(Long.parseLong(s));
        System.out.printf("Deleted %s", gameEditDto.getTitle());
    }

    @Override
    public void showAllGames() {
        List<Game> allGames = gameRepository.findAll();
        for (Game game : allGames) {
            AllGameViewDto gameViewDto = modelMapper.map(game, AllGameViewDto.class);
            System.out.printf("%s %s%n", gameViewDto.getTitle(), gameViewDto.getPrice().toString());
        }

    }


}

