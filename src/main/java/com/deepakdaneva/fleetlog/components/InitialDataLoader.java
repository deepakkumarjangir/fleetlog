/**
 * FleetLog
 * Apr 26, 2019 6:52:25 PM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.components;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.deepakdaneva.fleetlog.entities.Privilege;
import com.deepakdaneva.fleetlog.entities.Role;
import com.deepakdaneva.fleetlog.entities.User;
import com.deepakdaneva.fleetlog.repositories.PrivilegeRepository;
import com.deepakdaneva.fleetlog.repositories.RoleRepository;
import com.deepakdaneva.fleetlog.repositories.UserRepository;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	public static final Logger logger = LoggerFactory.getLogger(InitialDataLoader.class);

	@Value("${app.system.user.created}")
	boolean systemUserCreated;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${app.system.user.firstname}")
	private String systemUserFirstname;

	@Value("${app.system.user.lastname}")
	private String systemUserLastname;

	@Value("${app.system.user.password}")
	private String systemUserPassword;

	@Value("${app.system.user.email}")
	private String systemUserEmail;

	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (systemUserCreated) {
			return;
		}
		Privilege readPrivilege = createPrivilegeIfNotFound("READ");
		Privilege writePrivilege = createPrivilegeIfNotFound("WRITE");

		Set<Privilege> adminPrivileges = new HashSet<>();
		adminPrivileges.add(readPrivilege);
		adminPrivileges.add(writePrivilege);		
		createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
		
		Set<Privilege> userPrivileges = new HashSet<>();
		userPrivileges.add(readPrivilege);
		createRoleIfNotFound("ROLE_USER", userPrivileges);

		Optional<Role> optionalAdminRole = roleRepository.findByName("ROLE_ADMIN");
		Set<Role> finalUserRoles = new HashSet<>();
		finalUserRoles.add(optionalAdminRole.get());
		
		if (optionalAdminRole.isPresent()) {
			User user = new User();
			user.setUsername(systemUserEmail);
			user.setFirstName(systemUserFirstname);
			user.setLastName(systemUserLastname);
			user.setPassword(passwordEncoder.encode(systemUserPassword));
			user.setEmail(systemUserEmail);
			user.setRoles(finalUserRoles);
			user.setEnabled(true);
			user.setCreatedBy(systemUserEmail);
			userRepository.save(user);
		} else {
			logger.debug("ROLE NOT FOUND: ADMIN");
		}

		systemUserCreated = true;
	}

	@Transactional
	private Privilege createPrivilegeIfNotFound(String name) {
		Optional<Privilege> optionalPrivilege = privilegeRepository.findByName(name);
		if (optionalPrivilege.isPresent()) {
			return optionalPrivilege.get();
		} else {
			return privilegeRepository.save(new Privilege(name));
		}
	}

	@Transactional
	private Role createRoleIfNotFound(String name, Set<Privilege> privileges) {
		Optional<Role> optionalRole = roleRepository.findByName(name);
		if (optionalRole.isPresent()) {
			return optionalRole.get();
		} else {
			Role newRole = new Role(name);
			newRole.setPrivileges(privileges);
			return roleRepository.save(newRole);
		}
	}
	
}