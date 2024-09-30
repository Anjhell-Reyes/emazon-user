package com.bootcamp.emazon.user.domain.usecase;

import com.bootcamp.emazon.user.domain.api.IUserAccountServicePort;
import com.bootcamp.emazon.user.domain.exception.*;
import com.bootcamp.emazon.user.domain.model.Role;
import com.bootcamp.emazon.user.domain.model.User;
import com.bootcamp.emazon.user.domain.spi.IPasswordEncoder;
import com.bootcamp.emazon.user.domain.spi.IRolePersistencePort;
import com.bootcamp.emazon.user.domain.spi.IUserAccountPersistencePort;
import com.bootcamp.emazon.user.domain.util.DomainConstants;

import java.time.LocalDate;
import java.time.Period;

public class UserAccountUseCase implements IUserAccountServicePort {

    private final IUserAccountPersistencePort userAccountPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final IPasswordEncoder passwordEncoder;

    public UserAccountUseCase(IUserAccountPersistencePort userAccountPersistencePort, IRolePersistencePort rolePersistencePort, IPasswordEncoder passwordEncoder) {
        this.userAccountPersistencePort = userAccountPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUserAccount(User user) {

        //Validate required fields
        if(user.getName() == null){
            throw new NameNotNullException();
        }

        if(user.getLastName() == null){
            throw new LastNameNotNullException();
        }

        if (user.getBirthdate() == null){
            throw new BirthdateNotNullException();
        }

        if (user.getEmail() == null){
            throw new EmailNotNullException();
        }

        if (user.getPhone() == null){
            throw new PhoneNotNullException();
        }

        if (user.getPassword() == null){
            throw new PasswordNotNullException();
        }

        if (user.getIdentityDocument() == null){
            throw new IdentityDocumentNotNullException();
        }

        //Validate if the user is of legal age
        if (Period.between(user.getBirthdate(), LocalDate.now()).getYears() < DomainConstants.Validations.MIN_AGE) {
            throw new UnderageException();
        }

        //Validate if the email is valid
        if (!isValidEmail(user.getEmail())) {
            throw new InvalidEmailFormatException();
        }

        //Validate the maximum size of the phone number
        if(user.getPhone().length() > DomainConstants.Validations.MAX_LENGHT_PHONE){
            throw new PhoneMaxCharactersException();
        }

        //Validate if the identity document has only numbers
        if(!isIdentityDocumentValid(user.getIdentityDocument())){
            throw new InvalidIdentityDocumentFormatException();
        }

        user.setPassword(passwordEncoder.encryptPassword(user.getPassword()));

        // Assign the role "aux_bodega"
        Role role = rolePersistencePort.getRoleByName(DomainConstants.Roles.AUX_ROLE);
        user.setRole(role);

        return userAccountPersistencePort.saveUserAccount(user);
    }

    private boolean isValidEmail(String email) {
        return DomainConstants.Validations.EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isIdentityDocumentValid(String identityDocument) {
        return identityDocument != null && identityDocument.matches(DomainConstants.Validations.NUMERIC_PATTERN);
    }
}
