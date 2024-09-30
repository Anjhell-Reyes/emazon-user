package com.bootcamp.emazon.user.infraestructure.output.adapter;

import com.bootcamp.emazon.user.domain.model.User;
import com.bootcamp.emazon.user.infraestructure.output.entity.UserEntity;
import com.bootcamp.emazon.user.infraestructure.output.mapper.UserEntityMapper;
import com.bootcamp.emazon.user.infraestructure.output.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserJpaAdapterTest {

    @InjectMocks
    private UserAccountJpaAdapter userAccountJpaAdapter;

    @Mock
    private IUserRepository userAccountRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    private User user;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        user = new User("John", "Doe","12345678","+573234567890", LocalDate.of(2000, 1, 1),"john.doe@example.com","password"); // Crea una instancia simulada de User.
        userEntity = new UserEntity();
    }

    @Test
    void testGetUserAccountRepository() {
        IUserRepository repository = userAccountJpaAdapter.getUserAccountRepository();
        assertNotNull(repository);
        assertSame(userAccountRepository, repository);
    }

    @Test
    void testGetUserAccountEntityMapper() {
        UserEntityMapper mapper = userAccountJpaAdapter.getUserEntityMapper();
        assertNotNull(mapper);
        assertSame(userEntityMapper, mapper);
    }

    @Test
    void saveUserAccount_success() {
        when(userEntityMapper.toEntity(user)).thenReturn(userEntity);

        User savedUser = userAccountJpaAdapter.saveUserAccount(user);

        verify(userAccountRepository).save(userEntity);
        assertEquals(user, savedUser);
    }

}