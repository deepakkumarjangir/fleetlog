/**
 * FleetLog
 * Apr 26 8:16:07 pm
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepakdaneva.fleetlog.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findById(long id);

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
