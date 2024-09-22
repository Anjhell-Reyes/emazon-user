package com.bootcamp.emazon.user.application.mapper;

import com.bootcamp.emazon.user.application.dto.UserAccountRequest;
import com.bootcamp.emazon.user.domain.model.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserAccountMapper {

    UserAccount toUserAccount(UserAccountRequest userAccountRequest);
}
