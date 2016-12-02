package sg.ntu.itcm.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sg.ntu.itcm.dto.UserDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
	private static Logger log=LoggerFactory.getLogger(PasswordMatchesValidator.class);

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final UserDto user = (UserDto) obj;
        log.info("password is invalid");
        return user.getPassword().equals(user.getMatchingPassword());
    }

}
