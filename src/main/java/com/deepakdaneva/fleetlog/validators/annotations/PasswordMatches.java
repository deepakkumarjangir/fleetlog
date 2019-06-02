/**
 * FleetLog
 * Apr 26, 2019 9:49:01 AM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.validators.annotations;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.deepakdaneva.fleetlog.validators.PasswordMatchesValidator;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {
	String message() default "password and matchingPassword did not match";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
