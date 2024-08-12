package com.menes.course.testing;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestingApplicationTests {
    @Autowired
    private  MockMvc mockMvc;

    @Test
    public void itShouldReturns3UsersRecord() throws Exception {
        mockMvc.perform(get("/api/v1/users?page=0&size=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(3));
    }

}
