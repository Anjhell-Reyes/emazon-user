package com.bootcamp.emazon.user.domain.spi;

import com.bootcamp.emazon.user.domain.model.User;

public interface IJwtServicePersistencePort {
    String generate(User user);
}
