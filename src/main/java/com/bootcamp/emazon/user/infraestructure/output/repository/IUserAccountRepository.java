package com.bootcamp.emazon.user.infraestructure.output.repository;

import com.bootcamp.emazon.user.infraestructure.output.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserAccountRepository extends JpaRepository<UserAccountEntity, Long> {
}
