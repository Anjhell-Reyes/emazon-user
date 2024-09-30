package com.bootcamp.emazon.user.application.handler;

import com.bootcamp.emazon.user.application.dto.UserRequest;
import com.bootcamp.emazon.user.application.handler.user.UserAccountHandler;
import com.bootcamp.emazon.user.application.mapper.UserMapper;
import com.bootcamp.emazon.user.domain.api.IUserAccountServicePort;
import com.bootcamp.emazon.user.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserHandlerTest {

    @InjectMocks
    private UserAccountHandler userAccountHandler;

    @Mock
    private IUserAccountServicePort userAccountServicePort;

    @Mock
    private UserMapper userMapper;

    private UserRequest userRequest;
    private User user;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
        user = new User("John", "Doe","12345678","+573234567890", LocalDate.of(2000, 1, 1),"john.doe@example.com","password");
    }

    @Test
    void createUserAccount_success() {
        when(userMapper.toUser(userRequest)).thenReturn(user);

        userAccountHandler.createUserAccount(userRequest);

        verify(userAccountServicePort).createUserAccount(user);
    }
}