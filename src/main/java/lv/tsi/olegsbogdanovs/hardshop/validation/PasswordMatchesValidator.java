package lv.tsi.olegsbogdanovs.hardshop.validation;

import lv.tsi.olegsbogdanovs.hardshop.web.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserDto> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(UserDto user, ConstraintValidatorContext context){
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
