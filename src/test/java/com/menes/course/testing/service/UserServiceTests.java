package com.menes.course.testing.service;

import com.menes.course.testing.dto.UserDto;
import com.menes.course.testing.dto.mapper.impl.UserDtoMapperImpl;
import com.menes.course.testing.entity.User;
import com.menes.course.testing.repository.AddressRepository;
import com.menes.course.testing.repository.UserRepository;
import com.menes.course.testing.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class UserServiceTests {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDtoMapperImpl userDtoMapper;

    @Mock
    private AddressRepository addressRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock users
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setName("Men");
        user1.setUsername("username1");
        user1.setPassword("password1");
        users.add(user1);

        User user2 = new User();
        user2.setName("Linh");
        user2.setUsername("username2");
        user2.setPassword("password2");
        users.add(user2);

        // Mock pageable users list
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> pagedUsers = new PageImpl<>(users, pageable, users.size());
        when(userRepository.findAll(pageable)).thenReturn(pagedUsers);

    }

    @Test
    void testGetAllUsers() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);

        // When
        List<UserDto> result = userService.getAllUsers(pageable);

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    void testGetUserById() {
        // given
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setUsername("username");
        user.setPassword("password");

        // Mocking the repository method
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Mocking the mapper
        UserDto userDto = UserDto.builder()
                .id(userId)
                .username("username")
                .build();
        when(userDtoMapper.apply(user)).thenReturn(userDto);

        // when
        UserDto result = userService.getUserById(userId);

        // then
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("username", result.getUsername());
    }
}
