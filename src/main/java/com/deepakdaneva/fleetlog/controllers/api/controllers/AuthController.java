/**
 * FleetLog
 * May 12, 2019 12:03:19 AM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.controllers.api.controllers;

import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepakdaneva.fleetlog.configurations.security.jwt.JwtTokenProvider;
import com.deepakdaneva.fleetlog.controllers.api.requests.LoginRequest;
import com.deepakdaneva.fleetlog.controllers.api.responses.JwtTokenReponse;
import com.deepakdaneva.fleetlog.controllers.api.responses.LogoutResponse;
import com.deepakdaneva.fleetlog.controllers.api.responses.StandardAPIErrorResponse;
import com.deepakdaneva.fleetlog.entities.SessionToken;
import com.deepakdaneva.fleetlog.repositories.SessionTokenRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	public static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	private SessionTokenRepository sessionTokenRepository;

	@PostMapping(path = "/login")
	public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwtToken = jwtTokenProvider.generateJwtToken(authentication);

			return ResponseEntity.status(HttpStatus.OK).body(new JwtTokenReponse(jwtToken));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new StandardAPIErrorResponse("Username or Password Incorrect", false, null, null));
		}
	}

	@PostMapping(path = "/logout")
	public ResponseEntity<Object> logout(HttpServletRequest httpServletRequest) {
		Optional<SessionToken> sessionToken = sessionTokenRepository.findByToken(httpServletRequest.getHeader("Authorization").replace("Bearer ", ""));
		if (sessionToken.isPresent()) {
			sessionTokenRepository.delete(sessionToken.get());
			return ResponseEntity.status(HttpStatus.OK).body(new LogoutResponse("Logged Out Successfully!"));
		} else {
			ArrayList<String> errors = new ArrayList<>();
			errors.add("user session not available on server to log out");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardAPIErrorResponse("Logout Error", true, errors, null));
		}
	}

}
