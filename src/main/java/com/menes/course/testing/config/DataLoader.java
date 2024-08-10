package com.menes.course.testing.config;

import com.menes.course.testing.entity.User;
import com.menes.course.testing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
@RequiredArgsConstructor
@Configuration
public class DataLoader {
    private final UserRepository userRepository;
    @Bean
    public CommandLineRunner run() {
        return args -> {
            loadUsers();
        };
    }

    private void loadUsers() {
        User user1 = new User();
        user1.setUsername("johndoe");
        user1.setPassword("password123");
        user1.setName("John Doe");
        user1.setDob(LocalDate.of(1990, 1, 15));

        User user2 = new User();
        user2.setUsername("janedoe");
        user2.setPassword("password456");
        user2.setName("Jane Doe");
        user2.setDob(LocalDate.of(1992, 2, 20));

        User user3 = new User();
        user3.setUsername("mikesmith");
        user3.setPassword("password789");

        userRepository.saveAll(List.of(user1,user2,user3));
    }
}