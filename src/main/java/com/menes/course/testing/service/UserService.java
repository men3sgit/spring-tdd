package com.menes.course.testing.service;

import com.menes.course.testing.dto.AddressDto;
import com.menes.course.testing.dto.requests.AddressCreateRequest;
import com.menes.course.testing.dto.requests.UserCreateRequest;
import com.menes.course.testing.dto.UserDto;
import com.menes.course.testing.dto.requests.UserUpdateRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers(Pageable pageable);

    UserDto getUserById(Long id);

    UserDto createUser(UserCreateRequest request);

    void updateUser(Long id, UserUpdateRequest request);

    void deleteUser(Long id);

    boolean existsById(Long id);

    List<AddressDto> getAllAddressesById(Long id);

    AddressDto addNewAddress(Long userId , AddressCreateRequest request);
}
