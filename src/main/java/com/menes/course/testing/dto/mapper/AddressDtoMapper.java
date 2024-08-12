package com.menes.course.testing.dto.mapper;

import com.menes.course.testing.dto.AddressDto;
import com.menes.course.testing.entity.Address;
import org.springframework.stereotype.Component;

import java.util.function.Function;

public interface AddressDtoMapper extends Function<Address, AddressDto> {

}
