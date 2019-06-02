/**
 * FleetLog
 * Apr 26, 2019 8:38:51 AM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.controllers.api.requests;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.deepakdaneva.fleetlog.validators.annotations.PasswordMatches;
import com.deepakdaneva.fleetlog.validators.annotations.ValidEmail;
import com.deepakdaneva.fleetlog.validators.annotations.ValidPassword;

@PasswordMatches
public class NewUserRequest implements Serializable {

	private static final long serialVersionUID = 8772371721108766774L;

	@NotNull(message = "firstName is null")
	@NotEmpty(message = "firstName is empty")
	private String firstName;

	private String middleName;

	@NotNull(message = "lastName is null")
	@NotEmpty(message = "lastName is empty")
	private String lastName;

	@NotNull(message = "password is null")
	@NotEmpty(message = "password is empty")
	@ValidPassword(message = "password must follow rules: character length between 8 - 16, 1 special , 1 digit, 1 uppercase, 1 lowercase, no whitespace")
	private String password;

	@NotNull(message = "matchingPassword is null")
	@NotEmpty(message = "matchingPassword is empty")
	private String matchingPassword;

	@NotNull(message = "email is null")
	@NotEmpty(message = "email is empty")
	@ValidEmail(message = "email is not valid")
	private String email;

	private Set<String> roles;

	/*
	 * Getters and Setters
	 */
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewUserRequest other = (NewUserRequest) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
}
