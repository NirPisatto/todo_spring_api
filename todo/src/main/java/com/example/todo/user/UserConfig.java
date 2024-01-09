package com.example.todo.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {

            User john = new User(
                    "John",
                    "misapisatto@gmail.com",
                    "123456");

            User maria = new User(
                    "Maria",
                    "misapisatto@gmail.com",
                    "123456");

            userRepository.saveAll(List.of(john, maria));
        };
    }
}
