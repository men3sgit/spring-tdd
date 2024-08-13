package com.menes.course.testing.service.impl;

import com.menes.course.testing.dto.AddressDto;
import com.menes.course.testing.dto.mapper.AddressDtoMapper;
import com.menes.course.testing.repository.AddressRepository;
import com.menes.course.testing.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressDtoMapper addressDtoMapper;

    @Override
    public List<AddressDto> getAllAddressesByUserId(Long userId) {
        return addressRepository.findAllByUserId(userId)
                .stream()
                .map(addressDtoMapper)
                .toList();
    }
}
