
package com.bootcamp.emazon.user.infraestructure.exceptionHandler;

import com.bootcamp.emazon.user.domain.util.DomainConstants;

public enum ExceptionResponse {
    NAME_NULL("Name must not be null"),
    LAST_NAME_NULL("Last name must not be null"),
    BIRTHDATE_NULL("Birthdate must not be null"),
    EMAIL_NULL("Email must not be null"),
    IDENTITY_DOCUMENT_NULL("Identity document must not be null"),
    PHONE_NULL("Phone must not be null"),
    PASSWORD_NULL("Password not be null"),
    INVALID_EMAIL("The email format is invalid"),
    PHONE_MAX_LENGHT("Phone must be " + DomainConstants.Validations.MAX_LENGHT_PHONE + " characters or less"),
    IDENTITY_DOCUMENT_CONTANIN_LETTER("The identity document must contain only numbers"),
    UNDERAGE_USER("Age must be over " + DomainConstants.Validations.MIN_AGE + " years old"),
    ROLE_NOT_FOUND("Role not found"),
    USER_NOT_FOUND("User not found");
    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
