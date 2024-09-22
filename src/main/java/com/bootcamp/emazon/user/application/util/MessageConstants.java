package com.bootcamp.emazon.user.application.util;

public class MessageConstants {

    private MessageConstants() {
        // Constructor privado para evitar la instanciación
    }

    public static final class MessageExceptionUserAccount {

        public static final String NAME_NOT_NULL_MESSAGE = "The name cannot be null";
        public static final String NAME_NOT_EMPTY_MESSAGE = "The name cannot be empty";

        public static final String LAST_NAME_NOT_NULL_MESSAGE = "The last name cannot be null";
        public static final String LAST_NAME_NOT_EMPTY_MESSAGE = "Last name cannot be empty";

        public static final String IDENTITY_DOCUMENT_NOT_NULL_MESSAGE = "The identity document cannot be null";
        public static final String IDENTITY_DOCUMENT_NOT_EMPTY_MESSAGE = "The identity document cannot be empty";
        public static final String IDENTITY_DOCUMENT_NUMERIC = "The identity document should be numeric";

        public static final String PHONE_NOT_NULL_MESSAGE = "The phone cannot be null";
        public static final String PHONE_NOT_EMPTY_MESSAGE = "The phone cannot be empty";
        public static final String PHONE_SIZE_MESSAGE = "The phone cannot have more than 13 digits";

        public static final String BIRTHDATE_NOT_NULL_MESSAGE = "The birthdate cannot be null";

        public static final String EMAIL_NOT_NULL_MESSAGE = "The email cannot be null";
        public static final String EMAIL_NOT_EMPTY_MESSAGE = "The email cannot be empty";
        public static final String EMAIL_VALID_FORMAT_MESSAGE = "The email must be in a valid format";

        public static final String PASSWORD_NOT_NULL_MESSAGE = "The password cannot be null";
        public static final String PASSWORD_NOT_EMPTY_MESSAGE = "The password cannot be empty";
    }
}
