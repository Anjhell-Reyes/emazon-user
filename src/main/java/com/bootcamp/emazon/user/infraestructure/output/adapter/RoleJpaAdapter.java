package com.bootcamp.emazon.user.infraestructure.output.adapter;

import com.bootcamp.emazon.user.domain.exception.RoleNotFoundException;
import com.bootcamp.emazon.user.domain.model.Role;
import com.bootcamp.emazon.user.domain.spi.IRolePersistencePort;
import com.bootcamp.emazon.user.infraestructure.output.mapper.RoleEntityMapper;
import com.bootcamp.emazon.user.infraestructure.output.repository.IRoleRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final RoleEntityMapper roleEntityMapper;

    private final IRoleRepository roleRepository;

    @Override
    public Role getRoleByName(String roleName) {
        return roleEntityMapper.toRole(roleRepository.findByName(roleName).orElseThrow(RoleNotFoundException::new));
    }
}
