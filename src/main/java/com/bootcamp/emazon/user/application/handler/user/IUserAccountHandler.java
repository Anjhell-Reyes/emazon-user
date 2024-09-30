package com.bootcamp.emazon.user.application.handler.user;

import com.bootcamp.emazon.user.application.dto.UserRequest;

public interface IUserAccountHandler {

    void createUserAccount(UserRequest userRequest);
}
