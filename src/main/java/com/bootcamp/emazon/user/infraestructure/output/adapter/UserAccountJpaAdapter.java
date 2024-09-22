package com.bootcamp.emazon.user.infraestructure.output.adapter;

import com.bootcamp.emazon.user.domain.model.UserAccount;
import com.bootcamp.emazon.user.domain.spi.IUserAccountPersistencePort;
import com.bootcamp.emazon.user.infraestructure.output.mapper.UserAccountEntityMapper;
import com.bootcamp.emazon.user.infraestructure.output.repository.IUserAccountRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAccountJpaAdapter implements IUserAccountPersistencePort {

    private final IUserAccountRepository userAccountRepository;
    private final UserAccountEntityMapper userAccountEntityMapper;

    public IUserAccountRepository getUserAccountRepository() {
        return userAccountRepository;
    }

    public UserAccountEntityMapper getUserAccountEntityMapper() {
        return userAccountEntityMapper;
    }

    @Override
    public UserAccount saveUserAccount(UserAccount userAccount) {

        userAccountRepository.save(userAccountEntityMapper.toEntity(userAccount));
        return userAccount;
    }
}
