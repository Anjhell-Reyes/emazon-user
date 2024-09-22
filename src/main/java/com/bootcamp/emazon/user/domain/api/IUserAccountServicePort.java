package com.bootcamp.emazon.user.domain.api;

import com.bootcamp.emazon.user.domain.model.UserAccount;

public interface IUserAccountServicePort {

    UserAccount createUserAccount(UserAccount userAccount);
}
