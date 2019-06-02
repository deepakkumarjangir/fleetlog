/**
 * FleetLog
 * Apr 26, 2019 12:39:23 AM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.controllers.api.requests;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.deepakdaneva.fleetlog.validators.annotations.ValidEmail;

public class LoginRequest implements Serializable {

	private static final long serialVersionUID = -3545816912651314376L;

	@NotNull(message = "email is null")
	@NotEmpty(message = "email is empty")
	@ValidEmail(message = "email is not valid")
	private String email;

	@NotNull(message = "password is null")
	@NotEmpty(message = "password is empty")
	private String password;

	/*
	 * Getters and Setters
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
