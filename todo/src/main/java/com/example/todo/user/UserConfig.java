package com.example.todo.user;

import com.example.todo.task.Task;
import com.example.todo.task.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, TaskRepository taskRepository) {
        return args -> {
            User john = new User(
                    "firstname",
                    "lastname",
                    "misapisatto@gmail.com",
                    "123456");

            User maria = new User(
                    "firstname",
                    "lastname",
                    "misa545400@gmail.com",
                    "123456");

            userRepository.saveAll(List.of(john, maria));

            User test_user =  userRepository.findUserById(1L);

            Task todo1 = new Task("firstname", "lastname", "NEW", "LOW", test_user);
            Task todo2 = new Task("firstname", "lastname", "NEW", "LOW", test_user);
            taskRepository.saveAll(List.of(todo1, todo2));
        };
    }
}
