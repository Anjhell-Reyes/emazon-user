package com.bootcamp.emazon.user.infraestructure.output.adapter;

import com.bootcamp.emazon.user.domain.exception.UserNotFoundException;
import com.bootcamp.emazon.user.domain.model.User;
import com.bootcamp.emazon.user.infraestructure.exceptionHandler.ExceptionResponse;
import com.bootcamp.emazon.user.infraestructure.output.entity.UserEntity;
import com.bootcamp.emazon.user.infraestructure.output.mapper.UserEntityMapper;
import com.bootcamp.emazon.user.infraestructure.output.repository.IUserRepository;
import com.bootcamp.emazon.user.infraestructure.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsJpaAdapter implements UserDetailsService {

    private final IUserRepository userAccountRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userEntityMapper.toUser(userAccountRepository.findByEmail(email).orElseThrow(UserNotFoundException::new));

        return new CustomUserDetails(user);
    }
}
