package com.bootcamp.emazon.user.domain.usecase;

import com.bootcamp.emazon.user.domain.exception.*;
import com.bootcamp.emazon.user.domain.model.Role;
import com.bootcamp.emazon.user.domain.model.User;
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
class UserUseCaseTest {

    @Mock
    private IUserAccountPersistencePort userAccountPersistencePort;

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @Mock
    private IPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserAccountUseCase userAccountUseCase;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("John", "Doe","12345678","+573234567890",LocalDate.of(2000, 1, 1),"john.doe@example.com","password");
    }

    @Test
    void testCreateUserAccount_Success() {

        Role role = new Role();
        when(rolePersistencePort.getRoleByName(DomainConstants.Roles.AUX_ROLE)).thenReturn(role);
        when(userAccountPersistencePort.saveUserAccount(user)).thenReturn(user);
        when(passwordEncoder.encryptPassword("password")).thenReturn("encryptedPassword");

        User createdUser = userAccountUseCase.createUserAccount(user);

        assertNotNull(createdUser);
        assertEquals(user, createdUser);
        verify(userAccountPersistencePort).saveUserAccount(user);
        verify(passwordEncoder).encryptPassword("password");
    }

    @Test
    void testCreateUserAccount_NullName() {
        user.setName(null);
        assertThrows(NameNotNullException.class, () -> userAccountUseCase.createUserAccount(user));
    }

    @Test
    void testCreateUserAccount_NullLastName() {
        user.setLastName(null);
        assertThrows(LastNameNotNullException.class, () -> userAccountUseCase.createUserAccount(user));
    }

    @Test
    void testCreateUserAccount_NullBirthdate() {
        user.setBirthdate(null);
        assertThrows(BirthdateNotNullException.class, () -> userAccountUseCase.createUserAccount(user));
    }

    @Test
    void testCreateUserAccount_NullEmail() {
        user.setEmail(null);
        assertThrows(EmailNotNullException.class, () -> userAccountUseCase.createUserAccount(user));
    }

    @Test
    void testCreateUserAccount_NullPhone() {
        user.setPhone(null);
        assertThrows(PhoneNotNullException.class, () -> userAccountUseCase.createUserAccount(user));
    }

    @Test
    void testCreateUserAccount_NullPassword() {
        user.setPassword(null);
        assertThrows(PasswordNotNullException.class, () -> userAccountUseCase.createUserAccount(user));
    }

    @Test
    void testCreateUserAccount_NullIdentityDocument() {
        user.setIdentityDocument(null);
        assertThrows(IdentityDocumentNotNullException.class, () -> userAccountUseCase.createUserAccount(user));
    }

    @Test
    void testCreateUserAccount_Underage() {
        user.setBirthdate(LocalDate.of(2010, 1, 1));
        assertThrows(UnderageException.class, () -> userAccountUseCase.createUserAccount(user));
    }

    @Test
    void testCreateUserAccount_InvalidEmailFormat() {
        user.setEmail("invalid-email");
        assertThrows(InvalidEmailFormatException.class, () -> userAccountUseCase.createUserAccount(user));
    }

    @Test
    void testCreateUserAccount_PhoneMaxCharacters() {
        user.setPhone("12345678901234567890");
        assertThrows(PhoneMaxCharactersException.class, () -> userAccountUseCase.createUserAccount(user));
    }

    @Test
    void testCreateUserAccount_InvalidIdentityDocumentFormat() {
        user.setIdentityDocument("1234A678");
        assertThrows(InvalidIdentityDocumentFormatException.class, () -> userAccountUseCase.createUserAccount(user));
    }
}