/**
 * FleetLog
 * Apr 26, 2019 12:30:55 AM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.deepakdaneva.fleetlog.entities.User;
import com.deepakdaneva.fleetlog.models.CustomUserDetails;
import com.deepakdaneva.fleetlog.repositories.UserRepository;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	public static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User foundUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND WITH USERNAME: " + username));
		return new CustomUserDetails(foundUser);
	}

	public CustomUserDetails loadUserById(long userId) throws UsernameNotFoundException {
		User foundUser = userRepository.findById(userId)
				.orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND WITH ID: " + userId));
		return new CustomUserDetails(foundUser);
	}

}
