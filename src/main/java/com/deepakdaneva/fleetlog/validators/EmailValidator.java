/**
 * FleetLog
 * Apr 26, 2019 9:37:12 AM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.deepakdaneva.fleetlog.validators.annotations.ValidEmail;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

	@Override
	public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
		return validateEmail(email);
	}

	private boolean validateEmail(String email) {
		return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
	}
}
