package com.menes.course.testing.service.impl;

import com.menes.course.testing.dto.UserDto;
import com.menes.course.testing.dto.UserUpdateRequest;
import com.menes.course.testing.repository.UserRepository;
import com.menes.course.testing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return List.of();
    }

    @Override
    public UserDto getUserById(Long id) {
        return null;
    }

    @Override
    public UserDto updateUser(Long id, UserUpdateRequest user) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}
