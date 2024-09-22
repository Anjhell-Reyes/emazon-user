package com.bootcamp.emazon.user.application.handler;

import com.bootcamp.emazon.user.application.dto.UserAccountRequest;

public interface IUserAccountHandler {

    void createUserAccount(UserAccountRequest userAccountRequest);
}
