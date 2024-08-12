package com.menes.course.testing.dto.mapper.impl;

import com.menes.course.testing.dto.UserDto;
import com.menes.course.testing.dto.mapper.AddressDtoMapper;
import com.menes.course.testing.dto.mapper.UserDtoMapper;
import com.menes.course.testing.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDtoMapperImpl implements UserDtoMapper {

    public final AddressDtoMapper addressDtoMapper;

    @Override
    public UserDto apply(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .dob(user.getDob())
                .username(user.getUsername())
                .addresses(user.getAddresses().stream().map(addressDtoMapper).toList())
                .build();
    }
}
