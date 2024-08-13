package com.menes.course.testing.service;

import com.menes.course.testing.dto.AddressDto;

import java.util.List;

public interface AddressService {
    List<AddressDto> getAllAddressesByUserId(Long userId);
}
