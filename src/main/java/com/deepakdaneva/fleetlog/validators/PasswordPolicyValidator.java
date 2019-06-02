/**
 * FleetLog
 * Apr 26, 2019 3:21:14 AM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.validators;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

import com.deepakdaneva.fleetlog.validators.annotations.ValidPassword;

public class PasswordPolicyValidator implements ConstraintValidator<ValidPassword, String> {

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		/*
		 * Defining the password policy
		 */
		final PasswordValidator passwordValidator = new PasswordValidator(Arrays.asList(
				// Length of the password should be between 8 - 16
				new LengthRule(8, 16),
				// Password must contain 1 UpperCase character
				new CharacterRule(EnglishCharacterData.UpperCase, 1),
				// Password must contain 1 LowerCase character
				new CharacterRule(EnglishCharacterData.LowerCase, 1),
				// Password must contain 1 Digit
				new CharacterRule(EnglishCharacterData.Digit, 1),
				// Password must contain 1 special character
				new CharacterRule(EnglishCharacterData.Special, 1),
				// Password must not contain any whitespace
				new WhitespaceRule()));

		final RuleResult passwordValidityResult = passwordValidator.validate(new PasswordData(password));

		return passwordValidityResult.isValid();
	}
}
