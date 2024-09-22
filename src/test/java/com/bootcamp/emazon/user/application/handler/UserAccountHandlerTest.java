package com.bootcamp.emazon.user.application.handler;

import com.bootcamp.emazon.user.application.dto.UserAccountRequest;
import com.bootcamp.emazon.user.application.mapper.UserAccountMapper;
import com.bootcamp.emazon.user.domain.api.IUserAccountServicePort;
import com.bootcamp.emazon.user.domain.model.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAccountHandlerTest {

    @InjectMocks
    private UserAccountHandler userAccountHandler;

    @Mock
    private IUserAccountServicePort userAccountServicePort;

    @Mock
    private UserAccountMapper userAccountMapper;

    private UserAccountRequest userAccountRequest;
    private UserAccount userAccount;

    @BeforeEach
    void setUp() {
        userAccountRequest = new UserAccountRequest();
        userAccount = new UserAccount("John", "Doe","12345678","+573234567890", LocalDate.of(2000, 1, 1),"john.doe@example.com","password");
    }

    @Test
    void createUserAccount_success() {
        when(userAccountMapper.toUserAccount(userAccountRequest)).thenReturn(userAccount);

        userAccountHandler.createUserAccount(userAccountRequest);

        verify(userAccountServicePort).createUserAccount(userAccount);
    }
}