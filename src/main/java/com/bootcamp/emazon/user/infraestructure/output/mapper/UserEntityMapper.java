package com.bootcamp.emazon.user.infraestructure.output.mapper;

import com.bootcamp.emazon.user.domain.model.User;
import com.bootcamp.emazon.user.infraestructure.output.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    UserEntity toEntity(User user);
    User toUser(UserEntity userEntity);
}
