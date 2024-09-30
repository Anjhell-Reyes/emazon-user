package com.bootcamp.emazon.user.infraestructure.input;

import com.bootcamp.emazon.user.application.dto.UserRequest;
import com.bootcamp.emazon.user.application.handler.user.IUserAccountHandler;
import com.bootcamp.emazon.user.infraestructure.util.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserAccountHandler userHandler;

    @Operation(summary = "Add a new auxiliar user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = ApiConstants.UserAccount.RESPONSE_201_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "400", description = ApiConstants.UserAccount.RESPONSE_400_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "500", description = ApiConstants.UserAccount.RESPONSE_500_DESCRIPTION, content = @Content)
    })
    @PostMapping("/create/aux_bodega")
    public ResponseEntity<Void> createUserAccount(@RequestBody UserRequest userRequest) {
        userHandler.createUserAccount(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
