package com.menes.course.testing.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class UserDto {
    private Long id;

    private String username;

    private String name;

    private LocalDate dob;

    private List<AddressDto> addresses;
}
