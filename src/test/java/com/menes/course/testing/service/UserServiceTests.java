package com.menes.course.testing.service;

import com.menes.course.testing.dto.UserDto;
import com.menes.course.testing.dto.mapper.impl.UserDtoMapperImpl;
import com.menes.course.testing.dto.requests.UserUpdateRequest;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class UserServiceTests {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDtoMapperImpl userDtoMapper;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Long userId = 1L;
        user = new User();
        user.setId(userId);
        user.setUsername("username");
        user.setPassword("password");
        user.setName("Men");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

    }

    @Test
    void testGetAllUsers() {
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
        List<UserDto> result = userService.getAllUsers(pageable);
        // then
        verify(userRepository, times(1)).findAll(pageable);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    void testGetUserById() {
        // given
        // Mocking the mapper
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .username("username")
                .build();
        when(userDtoMapper.apply(user)).thenReturn(userDto);

        // when
        UserDto result = userService.getUserById(user.getId());

        // then
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals("username", result.getUsername());
    }

    @Test
    public void testUpdateUserById() {
        // given

        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setName("Updatename");
        userUpdateRequest.setUsername("Updateusername");
        userUpdateRequest.setPassword("Updatepassword");

        User updatedUser = new User();
        updatedUser.setId(user.getId());
        updatedUser.setName(userUpdateRequest.getName());
        updatedUser.setUsername(userUpdateRequest.getUsername());
        updatedUser.setPassword(userUpdateRequest.getPassword());

        // when
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        // then
        userService.updateUser(user.getId(), userUpdateRequest);
        verify(userRepository, times(1)).findById(user.getId());
        verify(userRepository, times(1)).save(argThat(savedUser ->
                savedUser.getId().equals(user.getId()) &&
                savedUser.getName().equals("Updatename") &&
                savedUser.getUsername().equals("Updateusername") &&
                savedUser.getPassword().equals("Updatepassword")
        ));

        verifyNoMoreInteractions(userRepository);
    }


    @Test
    public void testDeleteUserById() {

        //when
        when(userRepository.existsById(user.getId())).thenReturn(true);
        // then
        userService.deleteUser(user.getId());
        verify(userRepository, times(1)).existsById(user.getId());
        verify(userRepository, times(1)).deleteById(user.getId());
        verifyNoMoreInteractions(userRepository);

        when(userRepository.existsById(user.getId())).thenReturn(false);
        assertFalse(userRepository.existsById(user.getId()), "User should no longer exist after deletion");

        // Optionally, test the behavior when the user does not exist
//        doThrow(new IllegalArgumentException("User not found")).when(userRepository).deleteById(anyLong());
//        assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(999L), "Should throw exception if user does not exist");

    }


}
