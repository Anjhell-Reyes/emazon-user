package com.bootcamp.emazon.user.domain.spi;

public interface IAuthenticationPersistencePort {
    void authenticate(String email, String password);
}
