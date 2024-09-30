package com.bootcamp.emazon.user.application.handler.authentication;

import com.bootcamp.emazon.user.application.dto.AuthenticationRequest;
import com.bootcamp.emazon.user.domain.model.authentication.AuthenticationResponse;

public interface IAuthenticationServiceHandler {

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

}
