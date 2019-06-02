/**
 * FleetLog
 * May 21, 2019 12:16:47 AM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepakdaneva.fleetlog.entities.SessionToken;

public interface SessionTokenRepository extends JpaRepository<SessionToken, Long> {
	Optional<SessionToken> findByToken(String token);
}
