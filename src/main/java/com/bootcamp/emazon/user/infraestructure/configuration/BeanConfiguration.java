package com.bootcamp.emazon.user.infraestructure.configuration;

import com.bootcamp.emazon.user.domain.api.IUserAccountServicePort;
import com.bootcamp.emazon.user.domain.spi.IPasswordEncoder;
import com.bootcamp.emazon.user.domain.spi.IRolePersistencePort;
import com.bootcamp.emazon.user.domain.spi.IUserAccountPersistencePort;
import com.bootcamp.emazon.user.domain.usecase.UserAccountUseCase;
import com.bootcamp.emazon.user.infraestructure.output.adapter.RoleJpaAdapter;
import com.bootcamp.emazon.user.infraestructure.output.adapter.UserAccountJpaAdapter;
import com.bootcamp.emazon.user.infraestructure.output.mapper.RoleEntityMapper;
import com.bootcamp.emazon.user.infraestructure.output.mapper.UserAccountEntityMapper;
import com.bootcamp.emazon.user.infraestructure.output.repository.IRoleRepository;
import com.bootcamp.emazon.user.infraestructure.output.repository.IUserAccountRepository;
import com.bootcamp.emazon.user.infraestructure.output.util.EncryptPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserAccountRepository userAccountRepository;
    private final UserAccountEntityMapper userAccountEntityMapper;
    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Bean
    public IUserAccountPersistencePort userAccountPersistencePort() {
        return new UserAccountJpaAdapter(userAccountRepository, userAccountEntityMapper);
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
}
