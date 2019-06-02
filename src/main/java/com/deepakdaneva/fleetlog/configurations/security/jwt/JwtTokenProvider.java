/**
 * FleetLog
 * Apr 29, 2019 8:00:09 PM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.configurations.security.jwt;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.deepakdaneva.fleetlog.entities.SessionToken;
import com.deepakdaneva.fleetlog.models.CustomUserDetails;
import com.deepakdaneva.fleetlog.repositories.SessionTokenRepository;
import com.deepakdaneva.fleetlog.utilities.JSONUtility;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

	// To set expiration time inside generated token
	@Value("${fleetlog.app.jwt.expiration-allowed}")
	private boolean isTokenExpirationAllowed;

	// Token Expiration time in milliseconds if not present then default is 24Hrs
	@Value("${fleetlog.app.jwt.expiration:86400000}")
	private int jwtExpiration;

	@Autowired
	private SessionTokenRepository sessionTokenRepository;

	private String jwtSignature;

	public String generateJwtToken(Authentication authentication) {

		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

		String randomSignature = UUID.randomUUID().toString();

		Date issuedAt = new Date();

		Date tokenExpirationTime = new Date((new Date()).getTime() + jwtExpiration);

		/*
		 * Generating the jwtToken below, if token expiration time is allowed then
		 * setting the expiration time else not.
		 */
		JwtBuilder jwtBuilder = Jwts.builder().setSubject(user.getId().toString()).setIssuedAt(issuedAt);
		if (isTokenExpirationAllowed) {
			jwtBuilder.setExpiration(tokenExpirationTime);
		}

		/*
		 * Creating scopes of the user using Roles and Privileges to add as a claim in final Generated JWT Token
		 */
		JSONArray scopesArray = new JSONArray();
		user.getRoles().stream().forEach(role -> {
			JSONArray privilegesArray = new JSONArray();
			role.getPrivileges().stream().forEach(privilege -> privilegesArray.put(privilege.getName()));
			JSONObject roleObject = new JSONObject();
			roleObject.put("role",role.getName());
			roleObject.put("privileges", privilegesArray);
			scopesArray.put(roleObject);			
		});	

		String generatedJwtToken = jwtBuilder.signWith(SignatureAlgorithm.HS512, randomSignature).addClaims(JSONUtility.jsonToMap(new JSONObject().put("scopes", scopesArray))).compact();

		/*
		 * Storing the token in database for future reference
		 */
		SessionToken sessionToken = new SessionToken();
		sessionToken.setUser(user);
		sessionToken.setToken(generatedJwtToken);
		sessionToken.setSignature(randomSignature);
		sessionToken.setIssuedAt(issuedAt);
		if (isTokenExpirationAllowed) {
			sessionToken.setExpiration(tokenExpirationTime);
		}
		sessionTokenRepository.save(sessionToken);

		return generatedJwtToken;
	}

	public boolean isValidJwtToken(String jwtToken) {
		try {
			Optional<SessionToken> optionalSessionToken = sessionTokenRepository.findByToken(jwtToken);
			SessionToken sessionToken = optionalSessionToken.orElseThrow(() -> new MalformedJwtException("Invalid JWT Token"));
			this.jwtSignature = sessionToken.getSignature();
			Jwts.parser().setSigningKey(sessionToken.getSignature()).parseClaimsJws(jwtToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature, token can not be validated");
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token");
		} catch (ExpiredJwtException e) {
			logger.error("Expired JWT token");
		} catch (UnsupportedJwtException e) {
			logger.error("Unsupported JWT token");
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty");
		} catch (Exception e) {
			logger.error("Exception while validating token");
		}

		return false;
	}

	public long getUserIdFromJwtToken(String jwtToken) {
		try {
			return Long.parseLong(Jwts.parser().setSigningKey(this.jwtSignature).parseClaimsJws(jwtToken).getBody().getSubject());
		} catch (Exception e) {
			logger.error("Exception while parsing the userid from jwt token: " + e.getMessage());
			return 0;
		}
	}

}
