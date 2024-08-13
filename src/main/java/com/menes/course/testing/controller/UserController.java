package com.menes.course.testing.controller;

import com.menes.course.testing.dto.AddressDto;
import com.menes.course.testing.dto.requests.AddressCreateRequest;
import com.menes.course.testing.dto.requests.UserCreateRequest;
import com.menes.course.testing.dto.UserDto;
import com.menes.course.testing.dto.requests.UserUpdateRequest;
import com.menes.course.testing.entity.User;
import com.menes.course.testing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(Pageable pageable) {
        List<UserDto> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {

        if (userService.existsById(id)) {
            UserDto user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreateRequest request) {
        UserDto createdUser = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest user) {
        if (userService.existsById(id)) {
            userService.updateUser(id, user);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.existsById(id)) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping(path = "/{id}/addresses")
    public ResponseEntity<AddressDto> addNewAddress(@PathVariable Long id, @RequestBody AddressCreateRequest request) {
        if (userService.existsById(id)) {
            var res = userService.addNewAddress(id, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(path = "/{id}/addresses")
    public ResponseEntity<List<AddressDto>> getAddressesById(@PathVariable Long id) {
        if (userService.existsById(id)) {
            var res = userService.getAllAddressesById(id);
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public static void main(String[] args) {
        String potentialNullText = args[0];
        Supplier<String> supplier = () -> Objects.requireNonNullElse(potentialNullText, "");
    }
}
