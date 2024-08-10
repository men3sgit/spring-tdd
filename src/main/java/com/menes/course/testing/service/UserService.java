package com.menes.course.testing.service;

import com.menes.course.testing.dto.UserCreateRequest;
import com.menes.course.testing.dto.UserDto;
import com.menes.course.testing.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto createUser(UserCreateRequest request);

    UserDto updateUser(Long id, UserUpdateRequest request);

    void deleteUser(Long id);

    boolean existsById(Long id);
}
