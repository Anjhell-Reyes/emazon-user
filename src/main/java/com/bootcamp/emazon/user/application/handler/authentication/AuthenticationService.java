package com.bootcamp.emazon.user.application.handler.authentication;

import com.bootcamp.emazon.user.application.dto.AuthenticationRequest;
import com.bootcamp.emazon.user.domain.model.authentication.AuthenticationResponse;
import com.bootcamp.emazon.user.application.mapper.AuthenticationMapper;
import com.bootcamp.emazon.user.domain.api.IAuthenticationServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationServiceHandler{

    private final IAuthenticationServicePort authenticationServicePort;
    private final AuthenticationMapper authenticationMapper;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        return authenticationServicePort.authenticate(authenticationMapper.toAuthentication(authenticationRequest));
    }
}