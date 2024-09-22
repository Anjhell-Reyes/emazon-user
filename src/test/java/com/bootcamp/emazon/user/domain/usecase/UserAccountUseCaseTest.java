package com.bootcamp.emazon.user.domain.usecase;

import com.bootcamp.emazon.user.domain.exception.*;
import com.bootcamp.emazon.user.domain.model.Role;
import com.bootcamp.emazon.user.domain.model.UserAccount;
import com.bootcamp.emazon.user.domain.spi.IPasswordEncoder;
import com.bootcamp.emazon.user.domain.spi.IRolePersistencePort;
import com.bootcamp.emazon.user.domain.spi.IUserAccountPersistencePort;
import com.bootcamp.emazon.user.domain.util.DomainConstants;
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
class UserAccountUseCaseTest {

    @Mock
    private IUserAccountPersistencePort userAccountPersistencePort;

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @Mock
    private IPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserAccountUseCase userAccountUseCase;

    private UserAccount userAccount;

    @BeforeEach
    void setUp() {
        userAccount = new UserAccount("John", "Doe","12345678","+573234567890",LocalDate.of(2000, 1, 1),"john.doe@example.com","password");
    }

    @Test
    void testCreateUserAccount_Success() {

        Role role = new Role();
        when(rolePersistencePort.getRoleByName(DomainConstants.DEFAULT_ROLE)).thenReturn(role);
        when(userAccountPersistencePort.saveUserAccount(userAccount)).thenReturn(userAccount);
        when(passwordEncoder.encryptPassword("password")).thenReturn("encryptedPassword");

        UserAccount createdUserAccount = userAccountUseCase.createUserAccount(userAccount);

        assertNotNull(createdUserAccount);
        assertEquals(userAccount, createdUserAccount);
        verify(userAccountPersistencePort).saveUserAccount(userAccount);
        verify(passwordEncoder).encryptPassword("password");
    }

    @Test
    void testCreateUserAccount_NullName() {
        userAccount.setName(null);
        assertThrows(NameNotNullException.class, () -> userAccountUseCase.createUserAccount(userAccount));
    }

    @Test
    void testCreateUserAccount_NullLastName() {
        userAccount.setLastName(null);
        assertThrows(LastNameNotNullException.class, () -> userAccountUseCase.createUserAccount(userAccount));
    }

    @Test
    void testCreateUserAccount_NullBirthdate() {
        userAccount.setBirthdate(null);
        assertThrows(BirthdateNotNullException.class, () -> userAccountUseCase.createUserAccount(userAccount));
    }

    @Test
    void testCreateUserAccount_NullEmail() {
        userAccount.setEmail(null);
        assertThrows(EmailNotNullException.class, () -> userAccountUseCase.createUserAccount(userAccount));
    }

    @Test
    void testCreateUserAccount_NullPhone() {
        userAccount.setPhone(null);
        assertThrows(PhoneNotNullException.class, () -> userAccountUseCase.createUserAccount(userAccount));
    }

    @Test
    void testCreateUserAccount_NullPassword() {
        userAccount.setPassword(null);
        assertThrows(PasswordNotNullException.class, () -> userAccountUseCase.createUserAccount(userAccount));
    }

    @Test
    void testCreateUserAccount_NullIdentityDocument() {
        userAccount.setIdentityDocument(null);
        assertThrows(IdentityDocumentNotNullException.class, () -> userAccountUseCase.createUserAccount(userAccount));
    }

    @Test
    void testCreateUserAccount_Underage() {
        userAccount.setBirthdate(LocalDate.of(2010, 1, 1));
        assertThrows(UnderageException.class, () -> userAccountUseCase.createUserAccount(userAccount));
    }

    @Test
    void testCreateUserAccount_InvalidEmailFormat() {
        userAccount.setEmail("invalid-email");
        assertThrows(InvalidEmailFormatException.class, () -> userAccountUseCase.createUserAccount(userAccount));
    }

    @Test
    void testCreateUserAccount_PhoneMaxCharacters() {
        userAccount.setPhone("12345678901234567890");
        assertThrows(PhoneMaxCharactersException.class, () -> userAccountUseCase.createUserAccount(userAccount));
    }

    @Test
    void testCreateUserAccount_InvalidIdentityDocumentFormat() {
        userAccount.setIdentityDocument("1234A678");
        assertThrows(InvalidIdentityDocumentFormatException.class, () -> userAccountUseCase.createUserAccount(userAccount));
    }
}