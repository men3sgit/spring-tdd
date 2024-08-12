package com.menes.course.testing.config;

import com.menes.course.testing.entity.Address;
import com.menes.course.testing.entity.User;
import com.menes.course.testing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Configuration
public class DataLoader {

    private final UserRepository userRepository;

    @Bean
    public CommandLineRunner run() {
        return args -> {
            // Creating a user
            User user1 = new User();
            user1.setUsername("johndoe");
            user1.setPassword("password123");
            user1.setName("John Doe");
            user1.setDob(LocalDate.of(1990, 1, 1));

// Creating addresses
            Address address1 = new Address();
            address1.setCountry("USA");
            address1.setStreet("123 Elm Street");
            address1.setCity("Springfield");
            address1.setState("IL");
            address1.setPostalCode("62701");

// Set the user for address1
            address1.setUser(user1);

            Address address2 = new Address();
            address2.setCountry("USA");
            address2.setStreet("456 Oak Avenue");
            address2.setCity("Shelbyville");
            address2.setState("IL");
            address2.setPostalCode("62565");

// Set the user for address2
            address2.setUser(user1);

// Adding addresses to the user
            Set<Address> addresses = new HashSet<>();
            addresses.add(address1);
            addresses.add(address2);
            user1.setAddresses(addresses);

// Saving the user and addresses
            userRepository.save(user1);

        };
    }

}
