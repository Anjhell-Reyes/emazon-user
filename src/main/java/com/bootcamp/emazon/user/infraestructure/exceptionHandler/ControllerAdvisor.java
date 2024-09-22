package com.bootcamp.emazon.user.infraestructure.exceptionHandler;

import com.bootcamp.emazon.user.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "Message";

    //required field errors
    @ExceptionHandler(NameNotNullException.class)
    public ResponseEntity<Map<String, String>> handleNameNotNullException(
            NameNotNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NAME_NULL.getMessage()));
    }

    @ExceptionHandler(LastNameNotNullException.class)
    public ResponseEntity<Map<String, String>> handleLastNameNotNullException(
            LastNameNotNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.LAST_NAME_NULL.getMessage()));
    }

    @ExceptionHandler(BirthdateNotNullException.class)
    public ResponseEntity<Map<String, String>> handleBirthdateNotNullException(
            BirthdateNotNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.BIRTHDATE_NULL.getMessage()));
    }

    @ExceptionHandler(EmailNotNullException.class)
    public ResponseEntity<Map<String, String>> handleEmailNotNullException(
            EmailNotNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.EMAIL_NULL.getMessage()));
    }

    @ExceptionHandler(IdentityDocumentNotNullException.class)
    public ResponseEntity<Map<String, String>> handleIdentityDocumentNotNullException(
            IdentityDocumentNotNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.IDENTITY_DOCUMENT_NULL.getMessage()));
    }

    @ExceptionHandler(PasswordNotNullException.class)
    public ResponseEntity<Map<String, String>> handlePasswordNotNullException(
            PasswordNotNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PASSWORD_NULL.getMessage()));
    }

    @ExceptionHandler(PhoneNotNullException.class)
    public ResponseEntity<Map<String, String>> handlePhoneNotNullException(
            PhoneNotNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PHONE_NULL.getMessage()));
    }

    //Invalid email error
    @ExceptionHandler(InvalidEmailFormatException.class)
    public ResponseEntity<Map<String, String>> handleInvalidEmailFormatException(
            InvalidEmailFormatException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_EMAIL.getMessage()));
    }

    //Lenght phone error
    @ExceptionHandler(PhoneMaxCharactersException.class)
    public ResponseEntity<Map<String, String>> handlePhoneMaxCharactersException(
            PhoneMaxCharactersException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PHONE_MAX_LENGHT.getMessage()));
    }

    //error that there are letters on the identity document
    @ExceptionHandler(InvalidIdentityDocumentFormatException.class)
    public ResponseEntity<Map<String, String>> handleInvalidIdentityDocumentFormatException(
            InvalidIdentityDocumentFormatException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.IDENTITY_DOCUMENT_CONTANIN_LETTER.getMessage()));
    }

    //underage error
    @ExceptionHandler(UnderageException.class)
    public ResponseEntity<Map<String, String>> handleUnderageException(
            UnderageException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.UNDERAGE_USER.getMessage()));
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRoleNotFoundException(
            RoleNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ROLE_NOT_FOUND.getMessage()));
    }
}
