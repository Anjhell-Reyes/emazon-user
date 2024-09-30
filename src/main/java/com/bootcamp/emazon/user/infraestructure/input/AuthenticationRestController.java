package com.bootcamp.emazon.user.infraestructure.input;

import com.bootcamp.emazon.user.application.dto.AuthenticationRequest;
import com.bootcamp.emazon.user.domain.model.authentication.AuthenticationResponse;
import com.bootcamp.emazon.user.application.handler.authentication.IAuthenticationServiceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final IAuthenticationServiceHandler authenticationServiceHandler;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
    ) {
        return  ResponseEntity.ok(authenticationServiceHandler.authenticate(authenticationRequest));
    }
}
