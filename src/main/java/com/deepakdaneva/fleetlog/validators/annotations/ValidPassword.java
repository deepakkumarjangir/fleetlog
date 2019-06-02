/**
 * FleetLog
 * Apr 26, 2019 3:17:04 AM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.validators.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.deepakdaneva.fleetlog.validators.PasswordPolicyValidator;

@Documented
@Constraint(validatedBy = PasswordPolicyValidator.class)
@Retention(RUNTIME)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
public @interface ValidPassword {

    String message() default "password is against the password policy";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
