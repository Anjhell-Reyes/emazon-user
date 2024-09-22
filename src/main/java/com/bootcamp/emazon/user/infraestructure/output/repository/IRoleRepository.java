package com.bootcamp.emazon.user.infraestructure.output.repository;

import com.bootcamp.emazon.user.infraestructure.output.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(String roleName);
}
