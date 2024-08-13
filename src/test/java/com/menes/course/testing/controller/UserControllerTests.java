package com.menes.course.testing.controller;

import com.menes.course.testing.dto.AddressDto;
import com.menes.course.testing.service.AddressService;
import com.menes.course.testing.service.UserService;
import com.menes.course.testing.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private AddressService addressService;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void itShouldReturns3UserRecords() throws Exception {
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("johndoe"));
    }

    @Test
    public void itShouldCreateANewUser() throws Exception {
        String newUserJson = """
                {
                "username":"janedoe",
                "password":"password123"
                }
                """;

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newUserJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("janedoe"));
    }

    @DirtiesContext
    @Test
    public void itShouldUpdateANewUser() throws Exception {
        String updateUserJson = """
                {
                "username":"Duy Men",
                "password":"password123"
                }
                """;

        mockMvc.perform(put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateUserJson))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Duy Men"));

    }

    @Test
    public void itShouldDeleteAUser() throws Exception {
        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void itShouldReturnAUserById() throws Exception {
        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("johndoe"));
    }


    @Test
    public void itShouldReturnNotFoundForNonExistentUser() throws Exception {
        mockMvc.perform(get("/api/v1/users/999"))
                .andExpect(status().isNotFound());
    }

    @DirtiesContext
    @Test
    public void itShouldCreateANewAddressForExistingUser() throws Exception {
        final Long userId = 1L;
        String newAddressJson = """
                {
                "country": "USA",
                "street": "123 Main St",
                "city": "Springfield",
                "state": "IL",
                "postalCode": "62704"
                }
                """;

        mockMvc.perform(post("/api/v1/users/{userId}/addresses", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newAddressJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.country").value("USA"))
                .andExpect(jsonPath("$.street").value("123 Main St"))
                .andExpect(jsonPath("$.city").value("Springfield"))
                .andExpect(jsonPath("$.state").value("IL"))
                .andExpect(jsonPath("$.postalCode").value("62704"));

    }

    @Test
    public void itShouldGetAllAddressesByUserId() throws Exception {
        Long userId = 1L;

        mockMvc.perform(get("/api/v1/users/{userId}/addresses", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].country").value("USA"))
                .andExpect(jsonPath("$[1].country").value("USB"))
                .andExpect(jsonPath("$[0].street").value("123 Main St"))
                .andExpect(jsonPath("$[1].street").value("456 Elm St"));
    }

    @Test
    public void itShouldReturnsAllAddressesByUserId() {
        Long userId = 1L;

        List<AddressDto> addresses = List.of(
                new AddressDto(1L, "123 Main St", "Springfield", "IL", "62704", "USA"),
                new AddressDto(3L, "456 Elm St", "Springfield", "IL", "62704", "USA")
        );

        when(addressService.getAllAddressesByUserId(anyLong())).thenReturn(addresses);

        // Use the mock in your test
        List<AddressDto> expectedAddresses = addressService.getAllAddressesByUserId(userId);

        // Verify interactions
        verify(addressService).getAllAddressesByUserId(userId);

        // Add assertions to verify the behavior
        assertNotNull(expectedAddresses); // Ensure the result is not null
        assertFalse(expectedAddresses.isEmpty());
        assertEquals(2, expectedAddresses.size()); // Ensure the list contains 2 addresses
        assertEquals(addresses, expectedAddresses); // Ensure the returned list matches the expected addresses
    }

}
