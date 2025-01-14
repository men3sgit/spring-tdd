package com.menes.course.testing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class AddressDto {
    private Long id;

    private String street;

    private String city;

    private String state;

    private String postalCode;

    private String country;
}
