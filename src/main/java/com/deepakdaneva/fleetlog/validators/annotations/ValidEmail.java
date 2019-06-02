/**
 * FleetLog
 * Apr 26, 2019 9:30:50 AM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.validators.annotations;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.deepakdaneva.fleetlog.validators.EmailValidator;

@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {
	String message() default "email is not Valid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
