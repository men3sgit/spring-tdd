package com.menes.course.testing.repository;

import com.menes.course.testing.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AddressRepositoryTests {
    @Autowired
    private AddressRepository addressRepository;

    @Test
    void testSaveAddress() {
        // Given
        Address address = new Address();
        address.setStreet("123 Main St");
        address.setState("IL");
        address.setCountry("USA");
        address.setCity("Springfield");
        address.setPostalCode("12345");

        // When
        Address savedAddress = addressRepository.save(address);

        // Then
        assertNotNull(savedAddress.getId());

        assertEquals("123 Main St", savedAddress.getStreet());
        assertEquals("Springfield", savedAddress.getCity());
        assertEquals("12345", savedAddress.getPostalCode());
    }

    @Test
    void testFindById() {
        // Given
        Address address = new Address();
        address.setStreet("456 Elm St");
        address.setCountry("USA");
        address.setState("IL");
        address.setCity("Springfield");
        address.setPostalCode("67890");
        Address savedAddress = addressRepository.save(address);

        // When
        Optional<Address> foundAddress = addressRepository.findById(savedAddress.getId());

        // Then
        assertTrue(foundAddress.isPresent());
        assertEquals("456 Elm St", foundAddress.get().getStreet());
        assertEquals("Springfield", foundAddress.get().getCity());
        assertEquals("67890", foundAddress.get().getPostalCode());
    }

    @Test
    void testFindByIdNotFound() {
        // When
        Optional<Address> foundAddress = addressRepository.findById(999L);

        // Then
        assertFalse(foundAddress.isPresent());
    }

    @Test
    void testDeleteAddress() {
        // Given
        Address address = new Address();
        address.setStreet("789 Oak St");
        address.setCity("Springfield");
        address.setCountry("USA");
        address.setState("IL");
        address.setPostalCode("11223");
        Address savedAddress = addressRepository.save(address);

        // When
        addressRepository.deleteById(savedAddress.getId());
        Optional<Address> foundAddress = addressRepository.findById(savedAddress.getId());

        // Then
        assertFalse(foundAddress.isPresent());
    }

}
