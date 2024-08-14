package com.menes.course.testing.service.impl;

import com.menes.course.testing.dto.AddressDto;
import com.menes.course.testing.dto.UserDto;
import com.menes.course.testing.dto.mapper.AddressDtoMapper;
import com.menes.course.testing.dto.mapper.UserDtoMapper;
import com.menes.course.testing.dto.requests.AddressCreateRequest;
import com.menes.course.testing.dto.requests.UserCreateRequest;
import com.menes.course.testing.dto.requests.UserUpdateRequest;
import com.menes.course.testing.entity.Address;
import com.menes.course.testing.entity.User;
import com.menes.course.testing.repository.UserRepository;
import com.menes.course.testing.service.AddressService;
import com.menes.course.testing.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final AddressService addressService;
    private final AddressDtoMapper addressDtoMapper;

    @Override
    public List<UserDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).stream().map(userDtoMapper).toList();
    }

    @Override
    public UserDto getUserById(Long id) {
        return userDtoMapper.apply(userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found")));
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
    public void updateUser(Long id, UserUpdateRequest request) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        existingUser.setPassword(request.getPassword());
        existingUser.setName(request.getName());
        existingUser.setDob(request.getDob());
        existingUser.setUsername(request.getUsername());
        existingUser.setAddresses(request.getAddresses());
        userRepository.save(existingUser);
        userDtoMapper.apply(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public List<AddressDto> getAllAddressesById(Long id) {
        return addressService.getAllAddressesByUserId(id);
    }

    @Override
    public AddressDto addNewAddress(Long userId, AddressCreateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Address newAddress = new Address();
        newAddress.setUser(user);
        newAddress.setStreet(request.getStreet());
        newAddress.setCity(request.getCity());
        newAddress.setState(request.getState());
        newAddress.setCountry(request.getCountry());
        newAddress.setPostalCode(request.getPostalCode());
        user.addAddress(newAddress);
        userRepository.save(user);
        return addressDtoMapper.apply(newAddress);
    }
}
