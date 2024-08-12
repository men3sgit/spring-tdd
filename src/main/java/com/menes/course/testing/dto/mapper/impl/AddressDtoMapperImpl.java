package com.menes.course.testing.dto.mapper.impl;

import com.menes.course.testing.dto.AddressDto;
import com.menes.course.testing.dto.mapper.AddressDtoMapper;
import com.menes.course.testing.entity.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AddressDtoMapperImpl implements AddressDtoMapper {
    @Override
    public AddressDto apply(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .country(address.getCountry())
                .state(address.getState())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .street(address.getStreet())
                .build();
    }
}
