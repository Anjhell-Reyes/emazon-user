package com.bootcamp.emazon.user.infraestructure.output.mapper;

import com.bootcamp.emazon.user.domain.model.Role;
import com.bootcamp.emazon.user.infraestructure.output.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RoleEntityMapper {

    Role toRole(RoleEntity roleEntity);
}
