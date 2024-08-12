package com.menes.course.testing.dto.mapper;

import com.menes.course.testing.dto.AddressDto;
import com.menes.course.testing.dto.UserDto;
import com.menes.course.testing.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;


public interface UserDtoMapper extends Function<User, UserDto> {

}
