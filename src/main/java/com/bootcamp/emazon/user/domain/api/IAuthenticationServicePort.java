package com.bootcamp.emazon.user.domain.api;

import com.bootcamp.emazon.user.domain.model.authentication.Authentication;
import com.bootcamp.emazon.user.domain.model.authentication.AuthenticationResponse;

public interface IAuthenticationServicePort {
    AuthenticationResponse authenticate(Authentication authentication);
}
