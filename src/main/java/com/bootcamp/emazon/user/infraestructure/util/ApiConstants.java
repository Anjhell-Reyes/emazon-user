package com.bootcamp.emazon.user.infraestructure.util;

public class ApiConstants {
    private ApiConstants() {
        // Constructor privado para evitar la instanciación
    }

    public static final class UserAccount {
        public static final String RESPONSE_201_DESCRIPTION = "User account successfully created";
        public static final String RESPONSE_400_DESCRIPTION = "Invalid request body for user account creation";
        public static final String RESPONSE_500_DESCRIPTION = "Internal server error while creating user account";
        public static final String REQUEST_BODY_DESCRIPTION = "Details of the user account to be created";
    }
}
