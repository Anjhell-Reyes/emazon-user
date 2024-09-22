package com.bootcamp.emazon.user.domain.spi;

public interface IPasswordEncoder {
    String encryptPassword(String plainPassword);
}
