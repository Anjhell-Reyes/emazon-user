package com.bootcamp.emazon.user.domain.api;

import com.bootcamp.emazon.user.domain.model.User;

public interface IUserAccountServicePort {

    User createUserAccount(User user);
}
