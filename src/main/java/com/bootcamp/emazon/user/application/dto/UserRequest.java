package com.bootcamp.emazon.user.application.dto;

import com.bootcamp.emazon.user.application.util.MessageConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequest {

    @NotNull(message = MessageConstants.MessageExceptionUserAccount.NAME_NOT_NULL_MESSAGE)
    @NotEmpty(message = MessageConstants.MessageExceptionUserAccount.NAME_NOT_EMPTY_MESSAGE)
    private String name;

    @NotNull(message = MessageConstants.MessageExceptionUserAccount.LAST_NAME_NOT_NULL_MESSAGE)
    @NotEmpty(message = MessageConstants.MessageExceptionUserAccount.LAST_NAME_NOT_EMPTY_MESSAGE)
    private String lastName;

    @NotNull(message = MessageConstants.MessageExceptionUserAccount.IDENTITY_DOCUMENT_NOT_NULL_MESSAGE)
    @NotEmpty(message = MessageConstants.MessageExceptionUserAccount.IDENTITY_DOCUMENT_NOT_EMPTY_MESSAGE)
    @Pattern(regexp = "\\d+", message =MessageConstants.MessageExceptionUserAccount.IDENTITY_DOCUMENT_NUMERIC)
    private String identityDocument;

    @NotNull(message = MessageConstants.MessageExceptionUserAccount.PHONE_NOT_NULL_MESSAGE)
    @NotEmpty(message = MessageConstants.MessageExceptionUserAccount.PHONE_NOT_EMPTY_MESSAGE)
    @Size(max = 13,message = MessageConstants.MessageExceptionUserAccount.PHONE_SIZE_MESSAGE)
    private String phone;

    @NotNull(message = MessageConstants.MessageExceptionUserAccount.BIRTHDATE_NOT_NULL_MESSAGE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @NotNull(message = MessageConstants.MessageExceptionUserAccount.EMAIL_NOT_NULL_MESSAGE)
    @NotEmpty(message = MessageConstants.MessageExceptionUserAccount.EMAIL_NOT_EMPTY_MESSAGE)
    @Email(message = MessageConstants.MessageExceptionUserAccount.EMAIL_VALID_FORMAT_MESSAGE)
    private String email;

    @NotNull(message = MessageConstants.MessageExceptionUserAccount.PASSWORD_NOT_NULL_MESSAGE)
    @NotEmpty(message = MessageConstants.MessageExceptionUserAccount.PASSWORD_NOT_EMPTY_MESSAGE)
    private String password;
}
