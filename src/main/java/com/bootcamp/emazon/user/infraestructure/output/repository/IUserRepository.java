package com.bootcamp.emazon.user.infraestructure.output.repository;

import com.bootcamp.emazon.user.infraestructure.output.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
}
