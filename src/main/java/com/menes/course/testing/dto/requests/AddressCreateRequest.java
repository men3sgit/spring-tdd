package com.menes.course.testing.dto.requests;

import lombok.Getter;

@Getter
public class AddressCreateRequest {
    private String street;

    private String city;

    private String state;

    private String postalCode;

    private String country;
}
