package com.menes.course.testing.repository;

import com.menes.course.testing.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @DirtiesContext
    @Test
    public void testFindById() {
        // given
        User user = new User();
        user.setUsername("test");
        user.setId(1L);
        userRepository.save(user);

        // when

        Optional<User> foundUser = userRepository.findById(1L);
        assertTrue(foundUser.isPresent());
        assertNotNull(foundUser.get());
        assertEquals(1L, foundUser.get().getId());

    }

    @DirtiesContext
    @Test
    public void testFindByIdNotFound() {
        // given
        User user = new User();
        user.setUsername("test");
        user.setId(1L);
        userRepository.save(user);

        // when
        Optional<User> foundUser = userRepository.findById(2L);

        // then
        assertFalse(foundUser.isPresent());

    }

}
