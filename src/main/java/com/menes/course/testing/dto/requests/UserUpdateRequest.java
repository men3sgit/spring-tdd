package com.menes.course.testing.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.menes.course.testing.entity.Address;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
public class UserUpdateRequest {
    private String username;

    private String password;

    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;

    private Set<Address> addresses;

    public Set<Address> getAddresses() {
        return addresses == null ? new HashSet<>() : addresses;
    }
}
