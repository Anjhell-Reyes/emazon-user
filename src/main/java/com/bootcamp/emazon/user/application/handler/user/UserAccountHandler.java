package com.bootcamp.emazon.user.application.handler.user;

import com.bootcamp.emazon.user.application.dto.UserRequest;
import com.bootcamp.emazon.user.application.mapper.UserMapper;
import com.bootcamp.emazon.user.domain.api.IUserAccountServicePort;
import com.bootcamp.emazon.user.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAccountHandler implements IUserAccountHandler {

    private final IUserAccountServicePort userAccountServicePort;
    private final UserMapper userMapper;

    @Override
    public void createUserAccount(UserRequest userRequest){
        User user = userMapper.toUser(userRequest);
        userAccountServicePort.createUserAccount(user);
    }
}
