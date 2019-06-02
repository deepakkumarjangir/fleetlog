/**
 * FleetLog
 * Apr 26 8:16:52 pm
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Where;

import com.deepakdaneva.fleetlog.validators.annotations.ValidEmail;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USERS")
@Where(clause = "DELETED = 0")
public class User implements Serializable {

	private static final long serialVersionUID = 1444395949092621770L;

	public User() {
	}

	public User(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.firstName = user.getFirstName();
		this.middleName = user.getMiddleName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.enabled = user.isEnabled();
		this.roles = user.getRoles();
		this.createdBy = user.createdBy;
	}

	public User(String username, String firstName, String middleName, String lastName, String email, String password,
			boolean enabled, Set<Role> roles, String createdBy) {
		this.username = username;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
		this.createdBy = createdBy;
	}

	@Id
	@GenericGenerator(name = "NATIVE", strategy = "native")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "NATIVE")
	@Column(name = "ID", updatable = false)
	private Long id;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "FIRSTNAME", nullable = false)
	private String firstName;

	@Column(name = "MIDDLENAME")
	private String middleName;

	@Column(name = "LASTNAME", nullable = false)
	private String lastName;

	@NaturalId
	@ValidEmail
	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;

	@JsonIgnore
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "CREATEDBY", nullable = false)
	private String createdBy;

	@Column(name = "CREATEDON", nullable = false)
	private Date createdOn;

	@Column(name = "UPDATEDBY")
	private String updatedBy;

	@Column(name = "UPDATEDON")
	private Date updatedOn;

	@Column(name = "DELETEDBY")
	private String deletedBy;

	@Column(name = "DELETEDON")
	private Date deletedOn;

	@Column(name = "ENABLED", nullable = false)
	private boolean enabled = true;

	@Column(name = "DELETED", nullable = false)
	private boolean deleted = false;

	@Column(name = "ACCOUNTNONEXPIRED", nullable = false)
	private boolean accountNonExpired = true;

	@Column(name = "ACCOUNTNONLOCKED", nullable = false)
	private boolean accountNonLocked = true;

	@Column(name = "CREDENTIALSNONEXPIRED", nullable = false)
	private boolean credentialsNonExpired = true;

	@Column(name = "VERIFIED", nullable = false)
	private boolean verified = false;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "USERSROLES", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
	private Set<Role> roles;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<SessionToken> sessionTokens;

	/*
	 * Getters and Setters
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email != null) {
			this.email = email.toLowerCase();
		} else {
			this.email = email;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	@PrePersist
	public void setCreatedOn() {
		this.createdOn = new Date();
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	@PreUpdate
	public void setUpdatedOn() {
		this.updatedOn = new Date();
	}

	public String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Date getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(Date deletedOn) {
		this.deletedOn = deletedOn;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<SessionToken> getSessionTokens() {
		return sessionTokens;
	}

	public void setSessionTokens(Set<SessionToken> sessionTokens) {
		this.sessionTokens = sessionTokens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
