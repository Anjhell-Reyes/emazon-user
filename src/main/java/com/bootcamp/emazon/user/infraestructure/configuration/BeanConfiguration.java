package com.bootcamp.emazon.user.infraestructure.configuration;

import com.bootcamp.emazon.user.domain.api.IAuthenticationServicePort;
import com.bootcamp.emazon.user.domain.api.IUserAccountServicePort;
import com.bootcamp.emazon.user.domain.spi.*;
import com.bootcamp.emazon.user.domain.usecase.AuthenticationUseCase;
import com.bootcamp.emazon.user.domain.usecase.UserAccountUseCase;
import com.bootcamp.emazon.user.infraestructure.output.adapter.AuthenticationServiceAdapter;
import com.bootcamp.emazon.user.infraestructure.output.adapter.RoleJpaAdapter;
import com.bootcamp.emazon.user.infraestructure.output.adapter.UserAccountJpaAdapter;
import com.bootcamp.emazon.user.infraestructure.output.mapper.RoleEntityMapper;
import com.bootcamp.emazon.user.infraestructure.output.mapper.UserEntityMapper;
import com.bootcamp.emazon.user.infraestructure.output.repository.IRoleRepository;
import com.bootcamp.emazon.user.infraestructure.output.repository.IUserRepository;
import com.bootcamp.emazon.user.infraestructure.security.EncryptPassword;
import com.bootcamp.emazon.user.infraestructure.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userAccountRepository;
    private final UserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;
    private final AuthenticationManager authenticationManager;

    @Bean
    public IUserAccountPersistencePort userAccountPersistencePort() {
        return new UserAccountJpaAdapter(userAccountRepository, userEntityMapper);
    }

    @Bean
    public IUserAccountServicePort userAccountServicePort() {
        return new UserAccountUseCase(userAccountPersistencePort(), rolePersistencePort(), passwordEncoder());
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleJpaAdapter(roleEntityMapper, roleRepository);
    }

    @Bean
    public IPasswordEncoder passwordEncoder() {
        return new EncryptPassword();
    }

    @Bean
    public IAuthenticationServicePort authenticationServicePort() {
        return new AuthenticationUseCase(authenticationPersistencePort(),userAccountPersistencePort(),jwtServicePersistencePort());
    }

    @Bean
    public IAuthenticationPersistencePort authenticationPersistencePort() {
        return new AuthenticationServiceAdapter(authenticationManager);
    }

    @Bean
    public IJwtServicePersistencePort jwtServicePersistencePort(){
        return new JwtService();
    }

}
