/**
 * FleetLog
 * Apr 26, 2019 12:43:57 AM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.deepakdaneva.fleetlog.entities.Role;
import com.deepakdaneva.fleetlog.entities.User;

public class CustomUserDetails extends User implements UserDetails {

	private static final long serialVersionUID = 1170519943563172289L;

	public CustomUserDetails(final User user) {
		super(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getGrantedAuthorities(super.getRoles());
	}

	/*
	 * Getting all roles and privileges of the user and setting those as GrantedAuthority of the user
	 */

	private List<GrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		roles.stream().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
			role.getPrivileges().stream().forEach(privilege -> authorities.add(new SimpleGrantedAuthority(privilege.getName())));
		});
		return authorities;
	}
}
