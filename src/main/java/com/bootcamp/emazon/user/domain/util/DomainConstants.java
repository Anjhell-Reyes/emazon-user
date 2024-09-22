package com.bootcamp.emazon.user.domain.util;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.regex.Pattern;

public class DomainConstants {
    private DomainConstants() {
        // Constructor privado para evitar la instanciación
    }

    public static final String DEFAULT_ROLE = "aux_bodega";

    public static final class Validations {

        //Validation email
        public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        public static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

        //Validation phone
        public static final Integer MAX_LENGHT_PHONE = 13;

        //Validation age
        public static final Integer MIN_AGE = 18;
    }
}
