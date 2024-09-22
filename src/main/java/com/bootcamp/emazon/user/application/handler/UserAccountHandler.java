package com.bootcamp.emazon.user.application.handler;

import com.bootcamp.emazon.user.application.dto.UserAccountRequest;
import com.bootcamp.emazon.user.application.mapper.UserAccountMapper;
import com.bootcamp.emazon.user.domain.api.IUserAccountServicePort;
import com.bootcamp.emazon.user.domain.model.UserAccount;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAccountHandler implements IUserAccountHandler{

    private final IUserAccountServicePort userAccountServicePort;
    private final UserAccountMapper userAccountMapper;

    @Override
    public void createUserAccount(UserAccountRequest userAccountRequest){
        UserAccount userAccount = userAccountMapper.toUserAccount(userAccountRequest);
        userAccountServicePort.createUserAccount(userAccount);
    }
}
