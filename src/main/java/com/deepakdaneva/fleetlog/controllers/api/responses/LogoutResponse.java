/**
 * FleetLog
 * May 27, 2019 2:32:10 AM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.controllers.api.responses;

public class LogoutResponse {
	private String message;
	
	public LogoutResponse() {}
	
	public LogoutResponse(String message) {
		this.message = message;
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
}
