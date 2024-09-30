package com.bootcamp.emazon.user.infraestructure.output.adapter;

import com.bootcamp.emazon.user.domain.exception.RoleNotFoundException;
import com.bootcamp.emazon.user.domain.model.Role;
import com.bootcamp.emazon.user.domain.util.DomainConstants;
import com.bootcamp.emazon.user.infraestructure.output.entity.RoleEntity;
import com.bootcamp.emazon.user.infraestructure.output.mapper.RoleEntityMapper;
import com.bootcamp.emazon.user.infraestructure.output.repository.IRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleJpaAdapterTest {

    @InjectMocks
    private RoleJpaAdapter roleJpaAdapter;

    @Mock
    private RoleEntityMapper roleEntityMapper;

    @Mock
    private IRoleRepository roleRepository;

    private Role role;
    private RoleEntity roleEntity;
    private String roleName = DomainConstants.Roles.AUX_ROLE;

    @BeforeEach
    void setUp() {
        role = new Role();
        role.setName(roleName);
        roleEntity = new RoleEntity();
        roleEntity.setName(roleName);
    }

    @Test
    void getRoleByName_returnsRoleWhenExists() {
        when(roleRepository.findByName(roleName)).thenReturn(Optional.of(roleEntity));
        when(roleEntityMapper.toRole(roleEntity)).thenReturn(role);

        Role resultRole = roleJpaAdapter.getRoleByName(roleName);

        assertEquals(roleName, resultRole.getName());
        verify(roleRepository).findByName(roleName);
        verify(roleEntityMapper).toRole(roleEntity);
    }

    @Test
    void getRoleByName_roleNotFound() {
        when(roleRepository.findByName(roleName)).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> roleJpaAdapter.getRoleByName(roleName));
        verify(roleRepository).findByName(roleName);
    }
}