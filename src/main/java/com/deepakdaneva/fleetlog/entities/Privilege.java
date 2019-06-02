/**
 * FleetLog
 * Apr 26, 2019 6:24:15 PM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PRIVILEGES")
public class Privilege implements Serializable {

	private static final long serialVersionUID = -3690062805921832919L;

	public Privilege() {
	}

	public Privilege(String name) {
		this.name = name;
	}

	@Id
	@GenericGenerator(name = "NATIVE", strategy = "native")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "NATIVE")
	@Column(name = "ID", updatable = false)
	private Long id;

	@Column(name = "NAME", nullable = false, unique = true)
	private String name;

	// Mapping with the privileges property of the Role
	@ManyToMany(mappedBy = "privileges", cascade = CascadeType.ALL)
	private Set<Role> roles;

	/*
	 * Getters and Setters
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Privilege other = (Privilege) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
