package com.bootcamp.emazon.user.infraestructure.output.util;

import com.bootcamp.emazon.user.domain.spi.IPasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;

public class EncryptPassword implements IPasswordEncoder {

    @Override
    public String encryptPassword(String passwordPlain){

        String salt = BCrypt.gensalt();

        return BCrypt.hashpw(passwordPlain, salt);
    }

    public static Boolean verifyPassword(String passwordPlain, String passwordHashed){

        return BCrypt.checkpw(passwordPlain, passwordHashed);
    }

    public EncryptPassword(){

    }
}
