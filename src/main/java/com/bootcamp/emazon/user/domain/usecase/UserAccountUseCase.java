package com.bootcamp.emazon.user.domain.usecase;

import com.bootcamp.emazon.user.domain.api.IUserAccountServicePort;
import com.bootcamp.emazon.user.domain.exception.*;
import com.bootcamp.emazon.user.domain.model.Role;
import com.bootcamp.emazon.user.domain.model.UserAccount;
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
    public UserAccount createUserAccount(UserAccount userAccount) {

        //Validate required fields
        if(userAccount.getName() == null){
            throw new NameNotNullException();
        }

        if(userAccount.getLastName() == null){
            throw new LastNameNotNullException();
        }

        if (userAccount.getBirthdate() == null){
            throw new BirthdateNotNullException();
        }

        if (userAccount.getEmail() == null){
            throw new EmailNotNullException();
        }

        if (userAccount.getPhone() == null){
            throw new PhoneNotNullException();
        }

        if (userAccount.getPassword() == null){
            throw new PasswordNotNullException();
        }

        if (userAccount.getIdentityDocument() == null){
            throw new IdentityDocumentNotNullException();
        }

        //Validate if the user is of legal age
        if (Period.between(userAccount.getBirthdate(), LocalDate.now()).getYears() < DomainConstants.Validations.MIN_AGE) {
            throw new UnderageException();
        }

        //Validate if the email is valid
        if (!isValidEmail(userAccount.getEmail())) {
            throw new InvalidEmailFormatException();
        }

        //Validate the maximum size of the phone number
        if(userAccount.getPhone().length() > DomainConstants.Validations.MAX_LENGHT_PHONE){
            throw new PhoneMaxCharactersException();
        }

        //Validate if the identity document has only numbers
        if(!isIdentityDocumentValid(userAccount.getIdentityDocument())){
            throw new InvalidIdentityDocumentFormatException();
        }

        userAccount.setPassword(passwordEncoder.encryptPassword(userAccount.getPassword()));

        // Assign the role "aux_bodega"
        Role role = rolePersistencePort.getRoleByName(DomainConstants.DEFAULT_ROLE);
        userAccount.setRole(role);

        return userAccountPersistencePort.saveUserAccount(userAccount);
    }

    private boolean isValidEmail(String email) {
        return DomainConstants.Validations.EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isIdentityDocumentValid(String identityDocument) {
        return identityDocument != null && identityDocument.matches("\\d+");
    }
}
