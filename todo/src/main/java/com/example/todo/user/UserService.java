package com.example.todo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;
import java.lang.reflect.Field;

@Service("This is a service class")
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getUsers(PageRequest pageRequest) {
        return this.userRepository.findAllByOrderByIdAsc(pageRequest);
    }

    public void addNewUser(User user) {

        Optional<User> user_by_email = this.userRepository
                .findUserByEmail(user.getEmail());

        if (user_by_email.isPresent()) {
            throw new IllegalStateException("Email taken");
        }

        this.userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = this.userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("User with id " + userId + " does not exists");
        }
        this.userRepository.deleteById(userId);
    }

    @Transactional
    public void updateTheUser(){

    };

    public void updateUser(User user) {
        String[] stringArray = {"id", "create_date"};
        User user_by_id = this.userRepository
                .findUserById(user.getId());

        if (user_by_id == null) {
            throw new IllegalStateException("User with id " + user.getId() + " does not exists");
        }

        try {

            Field[] fields = user.getClass().getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(user);
                if (Arrays.asList(stringArray).contains(field.getName())) {
                    continue;
                }

                if (value != null && !field.getName().equals("id")) {
                    field.set(user_by_id, value);
                }
            }


//            System.out.println(user_by_id);

            this.userRepository.save(user_by_id);
        } catch (Exception e) {
            throw new IllegalStateException("Error: Invalid user data");
        }
    }
}
