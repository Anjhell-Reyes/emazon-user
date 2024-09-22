package com.bootcamp.emazon.user.infraestructure.input;

import com.bootcamp.emazon.user.application.dto.UserAccountRequest;
import com.bootcamp.emazon.user.application.handler.IUserAccountHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserAccountController.class)
class UserAccountControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserAccountHandler userAccountHandler;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testCreateUserAccount_Success() throws Exception {
        UserAccountRequest request = new UserAccountRequest();
        request.setName("John");
        request.setLastName("Doe");
        request.setEmail("john.doe@example.com");
        request.setPhone("1234567890");
        request.setPassword("securePassword");
        request.setIdentityDocument("123456789");

        Mockito.doNothing().when(userAccountHandler).createUserAccount(any(UserAccountRequest.class));

        mockMvc.perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        verify(userAccountHandler).createUserAccount(any(UserAccountRequest.class));
    }
}