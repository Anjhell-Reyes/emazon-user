package com.bootcamp.emazon.user.domain.spi;

import com.bootcamp.emazon.user.domain.model.Role;

public interface IRolePersistencePort {

    Role getRoleByName(String roleName);
}
