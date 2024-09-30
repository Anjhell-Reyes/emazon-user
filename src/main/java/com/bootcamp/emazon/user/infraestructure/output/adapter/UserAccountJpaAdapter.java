package com.bootcamp.emazon.user.infraestructure.output.adapter;

import com.bootcamp.emazon.user.domain.exception.UserNotFoundException;
import com.bootcamp.emazon.user.domain.model.User;
import com.bootcamp.emazon.user.domain.spi.IUserAccountPersistencePort;
import com.bootcamp.emazon.user.infraestructure.output.entity.UserEntity;
import com.bootcamp.emazon.user.infraestructure.output.mapper.UserEntityMapper;
import com.bootcamp.emazon.user.infraestructure.output.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAccountJpaAdapter implements IUserAccountPersistencePort {

    private final IUserRepository userAccountRepository;
    private final UserEntityMapper userEntityMapper;

    public IUserRepository getUserAccountRepository() {
        return userAccountRepository;
    }

    public UserEntityMapper getUserEntityMapper() {
        return userEntityMapper;
    }

    @Override
    public User saveUserAccount(User user) {

        userAccountRepository.save(userEntityMapper.toEntity(user));
        return user;
    }

    @Override
    public User getUserByEmail(String email){
        UserEntity user = userAccountRepository.findByEmail(email)
        .orElseThrow(UserNotFoundException::new);

        return userEntityMapper.toUser(user);
    }
}
