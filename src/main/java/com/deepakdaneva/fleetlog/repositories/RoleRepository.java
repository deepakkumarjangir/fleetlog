/**
 * FleetLog
 * Apr 26, 2019 6:55:32 PM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepakdaneva.fleetlog.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);
}
