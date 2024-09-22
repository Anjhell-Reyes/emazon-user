package com.bootcamp.emazon.user.infraestructure.output.adapter;

import com.bootcamp.emazon.user.domain.model.UserAccount;
import com.bootcamp.emazon.user.infraestructure.output.entity.UserAccountEntity;
import com.bootcamp.emazon.user.infraestructure.output.mapper.UserAccountEntityMapper;
import com.bootcamp.emazon.user.infraestructure.output.repository.IUserAccountRepository;
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
class UserAccountJpaAdapterTest {

    @InjectMocks
    private UserAccountJpaAdapter userAccountJpaAdapter;

    @Mock
    private IUserAccountRepository userAccountRepository;

    @Mock
    private UserAccountEntityMapper userAccountEntityMapper;

    private UserAccount userAccount;
    private UserAccountEntity userAccountEntity;

    @BeforeEach
    void setUp() {
        userAccount = new UserAccount("John", "Doe","12345678","+573234567890", LocalDate.of(2000, 1, 1),"john.doe@example.com","password"); // Crea una instancia simulada de UserAccount.
        userAccountEntity = new UserAccountEntity();
    }

    @Test
    void testGetUserAccountRepository() {
        IUserAccountRepository repository = userAccountJpaAdapter.getUserAccountRepository();
        assertNotNull(repository);
        assertSame(userAccountRepository, repository);
    }

    @Test
    void testGetUserAccountEntityMapper() {
        UserAccountEntityMapper mapper = userAccountJpaAdapter.getUserAccountEntityMapper();
        assertNotNull(mapper);
        assertSame(userAccountEntityMapper, mapper);
    }

    @Test
    void saveUserAccount_success() {
        when(userAccountEntityMapper.toEntity(userAccount)).thenReturn(userAccountEntity);

        UserAccount savedUserAccount = userAccountJpaAdapter.saveUserAccount(userAccount);

        verify(userAccountRepository).save(userAccountEntity);
        assertEquals(userAccount, savedUserAccount);
    }

}