package com.bootcamp.emazon.user.domain.usecase;

import com.bootcamp.emazon.user.domain.model.User;
import com.bootcamp.emazon.user.domain.model.authentication.Authentication;
import com.bootcamp.emazon.user.domain.model.authentication.AuthenticationResponse;
import com.bootcamp.emazon.user.domain.spi.IAuthenticationPersistencePort;
import com.bootcamp.emazon.user.domain.spi.IJwtServicePersistencePort;
import com.bootcamp.emazon.user.domain.spi.IUserAccountPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationUseCaseTest {

    @Mock
    private IAuthenticationPersistencePort authenticationPersistencePort;

    @Mock
    private IUserAccountPersistencePort userAccountPersistencePort;

    @Mock
    private IJwtServicePersistencePort jwtServicePersistencePort;

    @InjectMocks
    private AuthenticationUseCase authenticationUseCase;

    @Test
    void testAuthenticate_Success() {
        // Arrange: Configurar los datos de prueba
        String email = "test@example.com";
        String password = "password123";
        String expectedToken = "jwt-token";

        Authentication authentication = new Authentication(email, password);

        User user = new User();
        user.setEmail(email);

        // Configuramos el comportamiento de los mocks
        doNothing().when(authenticationPersistencePort).authenticate(email, password);
        when(userAccountPersistencePort.getUserByEmail(email)).thenReturn(user);
        when(jwtServicePersistencePort.generate(user)).thenReturn(expectedToken);

        // Act: Llamar al método que se va a probar
        AuthenticationResponse response = authenticationUseCase.authenticate(authentication);

        // Assert: Verificar que se comporta como se espera
        assertThat(response).isNotNull();
        assertThat(response.getToken()).isEqualTo(expectedToken);

        // Verificar que los mocks fueron llamados correctamente
        verify(authenticationPersistencePort, times(1)).authenticate(email, password);
        verify(userAccountPersistencePort, times(1)).getUserByEmail(email);
        verify(jwtServicePersistencePort, times(1)).generate(user);
    }

    @Test
    void testAuthenticate_UserNotFound() {
        // Arrange: Configurar los datos de prueba
        String email = "invalid@example.com";
        String password = "password123";

        Authentication authentication = new Authentication(email, password);

        // Configuramos el comportamiento de los mocks
        doNothing().when(authenticationPersistencePort).authenticate(email, password);
        when(userAccountPersistencePort.getUserByEmail(email)).thenThrow(new IllegalArgumentException("User not found"));

        // Act & Assert: Esperar una excepción
        try {
            authenticationUseCase.authenticate(authentication);
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage()).isEqualTo("User not found");
        }

        // Verificar que los mocks fueron llamados correctamente
        verify(authenticationPersistencePort, times(1)).authenticate(email, password);
        verify(userAccountPersistencePort, times(1)).getUserByEmail(email);
        verify(jwtServicePersistencePort, never()).generate(any());
    }
}