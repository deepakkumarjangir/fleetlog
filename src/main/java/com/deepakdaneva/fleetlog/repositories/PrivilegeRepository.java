/**
 * FleetLog
 * Apr 26, 2019 7:23:07 PM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepakdaneva.fleetlog.entities.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
	Optional<Privilege> findByName(String name);
}
