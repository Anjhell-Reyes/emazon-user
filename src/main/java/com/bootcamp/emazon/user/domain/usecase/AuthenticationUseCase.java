package com.bootcamp.emazon.user.domain.usecase;

import com.bootcamp.emazon.user.domain.model.User;
import com.bootcamp.emazon.user.domain.spi.IJwtServicePersistencePort;
import com.bootcamp.emazon.user.domain.spi.IUserAccountPersistencePort;
import com.bootcamp.emazon.user.domain.api.IAuthenticationServicePort;
import com.bootcamp.emazon.user.domain.model.authentication.Authentication;
import com.bootcamp.emazon.user.domain.model.authentication.AuthenticationResponse;
import com.bootcamp.emazon.user.domain.spi.IAuthenticationPersistencePort;

public class AuthenticationUseCase implements IAuthenticationServicePort {

    private final IAuthenticationPersistencePort authenticationPersistencePort;
    private final IUserAccountPersistencePort userAccountPersistencePort;
    private final IJwtServicePersistencePort jwtServicePersistencePort;

    public AuthenticationUseCase(IAuthenticationPersistencePort authenticationPersistencePort, IUserAccountPersistencePort userAccountPersistencePort, IJwtServicePersistencePort jwtServicePersistencePort) {
        this.authenticationPersistencePort = authenticationPersistencePort;
        this.userAccountPersistencePort = userAccountPersistencePort;
        this.jwtServicePersistencePort = jwtServicePersistencePort;
    }

    @Override
    public AuthenticationResponse authenticate(Authentication authentication) {

        authenticationPersistencePort.authenticate(authentication.getEmail(), authentication.getPassword());

        User user = userAccountPersistencePort.getUserByEmail(authentication.getEmail());

        var jwtToken = jwtServicePersistencePort.generate(user);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(jwtToken);

        return authenticationResponse;
    }
}
