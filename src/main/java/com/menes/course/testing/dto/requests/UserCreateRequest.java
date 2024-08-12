package com.menes.course.testing.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.menes.course.testing.entity.Address;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class UserCreateRequest {

    private String username;

    private String password;

    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;

    private Set<Address> addresses;

}
