package com.bootcamp.emazon.user.infraestructure.output.mapper;

import com.bootcamp.emazon.user.domain.model.UserAccount;
import com.bootcamp.emazon.user.infraestructure.output.entity.UserAccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserAccountEntityMapper {

    UserAccountEntity toEntity(UserAccount userAccount);
}
