package com.menes.course.testing.service.impl;

import com.menes.course.testing.dto.requests.UserCreateRequest;
import com.menes.course.testing.dto.UserDto;
import com.menes.course.testing.dto.requests.UserUpdateRequest;
import com.menes.course.testing.dto.mapper.UserDtoMapper;
import com.menes.course.testing.entity.User;
import com.menes.course.testing.repository.AddressRepository;
import com.menes.course.testing.repository.UserRepository;
import com.menes.course.testing.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final AddressRepository addressRepository;

    @Transactional
    @Override
    public List<UserDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).stream().map(userDtoMapper).toList();
    }

    @Override
    public UserDto getUserById(Long id) {
        return userDtoMapper.apply(userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("User not found")));
    }

    @Override
    public UserDto createUser(UserCreateRequest request) {
        User newUser = new User();
        newUser.setPassword(request.getPassword());
        newUser.setName(request.getName());
        newUser.setDob(request.getDob());
        newUser.setUsername(request.getUsername());
        User stored = userRepository.save(newUser);
        return userDtoMapper.apply(stored);
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

    @Override
    public List<User> getAllUsersTest() {
        return userRepository.findAll();
    }
}
