package com.bootcamp.emazon.user.domain.spi;

import com.bootcamp.emazon.user.domain.model.UserAccount;

public interface IUserAccountPersistencePort {

    UserAccount saveUserAccount(UserAccount userAccount);
}
