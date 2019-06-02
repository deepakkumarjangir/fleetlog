/**
 * FleetLog
 * Apr 26, 2019 4:00:21 PM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.controllers.api.responses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StandardAPIErrorResponse implements Serializable {

	private static final long serialVersionUID = 7790328548440143136L;

	private String message = "";
	private Object data = null;
	private boolean hasError = false;
	private List<String> errors = new ArrayList<>();

	public StandardAPIErrorResponse() {
	}

	public StandardAPIErrorResponse(String message, boolean hasError, List<String> errors, Object data) {
		this.message = message;
		this.hasError = hasError;
		this.data = data;
		if (errors != null) {
			this.errors = errors;
		}
	}

	/*
	 * Getters and Setters
	 */
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		if (errors != null) {
			this.errors = errors;
		}
	}
}
