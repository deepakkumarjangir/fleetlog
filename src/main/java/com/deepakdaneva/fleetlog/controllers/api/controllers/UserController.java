/**
 * FleetLog
 * Apr 26, 2019 10:06:23 PM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.controllers.api.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepakdaneva.fleetlog.controllers.api.requests.NewUserRequest;
import com.deepakdaneva.fleetlog.controllers.api.responses.StandardAPIErrorResponse;
import com.deepakdaneva.fleetlog.entities.Role;
import com.deepakdaneva.fleetlog.entities.User;
import com.deepakdaneva.fleetlog.models.CustomUserDetails;
import com.deepakdaneva.fleetlog.repositories.RoleRepository;
import com.deepakdaneva.fleetlog.repositories.UserRepository;
import com.deepakdaneva.fleetlog.services.EmailService;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping(path = "/createUser")
	public ResponseEntity<Object> createUser(Authentication authentication, @Valid @RequestBody NewUserRequest newUserRequest) {

        if(userRepository.existsByEmail(newUserRequest.getEmail())) {
        	ArrayList<String> errors = new ArrayList<>();
        	errors.add("User Already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardAPIErrorResponse("User Already Exists!",true,errors,null));
        }
        
        /*
         * Setting user roles below if not provided then setting the default ROLE_USER role.
         */
        Set<Role> finalUserRoles = new HashSet<>();
        if(newUserRequest.getRoles() != null && newUserRequest.getRoles().size() > 0) {
            newUserRequest.getRoles().stream().forEach(roleNameString -> {
                switch(roleNameString) {
                case "ROLE_ADMIN":
                	Role adminRole = roleRepository.findByName(roleNameString).orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                	finalUserRoles.add(adminRole);              
                  break;
                case "ROLE_USER":
                    Role userRole = roleRepository.findByName(roleNameString).orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    finalUserRoles.add(userRole);
                  break;
                default:
                    Role defaultUserRole = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    finalUserRoles.add(defaultUserRole);              
                }
            });
        } else {
            Role defaultUserRole = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
            finalUserRoles.add(defaultUserRole);
        }
        
        /*
         * Now finally creating the user.
         */
        User newUser = new User(newUserRequest.getEmail(), newUserRequest.getFirstName(), newUserRequest.getMiddleName(), 
				newUserRequest.getLastName(), newUserRequest.getEmail(), passwordEncoder.encode(newUserRequest.getPassword()), 
				true, finalUserRoles, ((CustomUserDetails)authentication.getPrincipal()).getEmail());

        userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.OK).body(new StandardAPIErrorResponse("User Created!", false, null, null));
	}

	@PostMapping(path = "/getUserDetails")
	public ResponseEntity<Object> getUserDetails(Authentication authentication) {
		// TODO: Write Logic to fetch the currect user details and send it back to the
		// requesting user in response
		return ResponseEntity.status(500).body(new Object());
	}

	@PostMapping(path = "/resetPassword")
	public ResponseEntity<Object> resetPassword(Authentication authentication) {
		// TODO: Write logic to reset the password of the user, detect if it's admin who
		// is changing the other user password or
		// changing owns password.
		return ResponseEntity.status(500).body(new Object());
	}

	@PostMapping(path = "/updateUser")
	public ResponseEntity<Object> updateUser(Authentication authentication) {
		// TODO: Write logic to update user, detect if it's admin who is changing other
		// user's profile or owns profile.
		return ResponseEntity.status(500).body(new Object());
	}

	@PostMapping(path = "/deleteUser")
	public ResponseEntity<Object> deleteUser(Authentication authentication) {
		// TODO: Write logic to delete user
		return ResponseEntity.status(500).body(new Object());
	}
}