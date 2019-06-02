/**
 * FleetLog
 * Apr 26, 2019 9:50:06 AM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.deepakdaneva.fleetlog.controllers.api.requests.NewUserRequest;
import com.deepakdaneva.fleetlog.validators.annotations.PasswordMatches;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
		NewUserRequest registerRequest = (NewUserRequest) object;
		return registerRequest.getPassword().equals(registerRequest.getMatchingPassword());
	}
}
