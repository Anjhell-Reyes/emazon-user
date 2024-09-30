package com.bootcamp.emazon.user.domain.spi;

import com.bootcamp.emazon.user.domain.model.User;

public interface IUserAccountPersistencePort {

    User saveUserAccount(User user);

    User getUserByEmail(String email);
}
