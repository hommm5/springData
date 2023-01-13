package com.example.spring_auto_mapping_exr.controllers;

import com.example.spring_auto_mapping_exr.domain.dtos.GameAddDto;
import com.example.spring_auto_mapping_exr.domain.dtos.UserLoginDto;
import com.example.spring_auto_mapping_exr.domain.dtos.UserRegisterDto;
import com.example.spring_auto_mapping_exr.services.GameService;
import com.example.spring_auto_mapping_exr.services.UserService;
import com.example.spring_auto_mapping_exr.utils.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class AppController implements CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final UserService userService;
    private final ValidationUtil validationUtil;
    private final GameService gameService;

    @Autowired
    public AppController(BufferedReader bufferedReader, UserService userService, ValidationUtil validationUtil, GameService gameService) {
        this.bufferedReader = bufferedReader;
        this.userService = userService;
        this.validationUtil = validationUtil;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {

        while (true) {

            System.out.println("Enter command:");

            String[] input = bufferedReader.readLine().split("\\|");
            switch (input[0]) {

                case "RegisterUser":

                    String email = input[1];
                    String pass = input[2];
                    String confirmationPass = input[3];
                    String name = input[4];

                    if (!pass.equals(confirmationPass)) {
                        System.out.println("Passwords do not match");
                        break;
                    }

                    UserRegisterDto userRegisterDto =
                            new UserRegisterDto(email, pass, name);

                    if (validationUtil.isValid(userRegisterDto)) {
                        userService.registerUser(userRegisterDto);
                        System.out.printf("User %s was registered%n", userRegisterDto.getFullName());
                    } else {
                        validationUtil.getViolations(userRegisterDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }

                    break;
                case "LoginUser":

                    String emailLog = input[1];
                    String passwordLog = input[2];

                    UserLoginDto userLoginDto = new UserLoginDto(emailLog, passwordLog);

                    userService.loginUser(userLoginDto);

                    break;
                case "Logout":
                    userService.logout();
                    break;
                case "AddGame":

                    String title = input[1];
                    BigDecimal price = new BigDecimal(input[2]);
                    double size = Double.parseDouble(input[3]);
                    String trailer = input[4];
                    String image = input[5];
                    String description = input[6];
                    LocalDate releaseDate = LocalDate.parse(input[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                    GameAddDto gameAddDto =
                            new GameAddDto(title, price, size, trailer, image, description, releaseDate);

                    if (validationUtil.isValid(gameAddDto)) {
                        gameService.addGame(gameAddDto);
                    } else {
                        validationUtil.getViolations(gameAddDto).stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                    break;

                case "EditGame":

                    gameService.editGame(input);

                    break;

                case "DeleteGame":

                    gameService.deleteGameById(input[1]);

                    break;
                case "AllGames":
                    gameService.showAllGames();
                    break;
                case "DetailGame":
                    break;
                case "OwnedGames":
                    break;
            }
        }
    }
}
