/**
 * FleetLog
 * May 27, 2019 3:02:26 AM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.controllers.api.dtos;

import java.util.Collection;
import java.util.Date;

import com.deepakdaneva.fleetlog.entities.Role;

public class UserDto {
	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private Date createdOn;
	Collection<Role> roles;

	public UserDto() {}
	
	public UserDto(Long id, String firstName, String middleName, String lastName, String email, Date createdOn,
			Collection<Role> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.createdOn = createdOn;
		this.roles = roles;
	}
	
	/*
	 * Getters and Setters
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
}
