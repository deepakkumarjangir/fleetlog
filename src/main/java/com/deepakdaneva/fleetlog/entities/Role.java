/**
 * FleetLog
 * Apr 26, 2019 11:36:13 PM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "ROLES")
public class Role implements Serializable {

	private static final long serialVersionUID = 6754973156025341905L;

	public Role() {
	}

	public Role(final String name) {
		this.name = name;
	}

	@Id
	@GenericGenerator(name = "NATIVE", strategy = "native")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "NATIVE")
	@Column(name = "ID", updatable = false)
	private Long id;

	@NaturalId
	@Column(name = "NAME", nullable = false, unique = true)
	private String name;

	// Mapping with the roles property of the User
	@ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
	private Set<User> users;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "ROLESPRIVILEGES", joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "PRIVILEGE_ID", referencedColumnName = "ID"))
	private Set<Privilege> privileges;

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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
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
		Role other = (Role) obj;
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
