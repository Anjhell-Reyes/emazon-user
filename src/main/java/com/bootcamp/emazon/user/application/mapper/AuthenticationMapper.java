package com.bootcamp.emazon.user.application.mapper;

import com.bootcamp.emazon.user.application.dto.AuthenticationRequest;
import com.bootcamp.emazon.user.domain.model.authentication.Authentication;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AuthenticationMapper {

    Authentication toAuthentication(AuthenticationRequest authenticationRequest);
}
