/**
 * FleetLog
 * May 20, 2019 11:03:43 PM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SESSIONTOKENS")
public class SessionToken implements Serializable {

	private static final long serialVersionUID = 650053685731280192L;

	public SessionToken() {
	}

	public SessionToken(User user, String token, String signature, Date issuedAt, Date expiration) {
		this.user = user;
		this.token = token;
		this.signature = signature;
		this.issuedAt = issuedAt;
		this.expiration = expiration;
	}

	@Id
	@GenericGenerator(name = "NATIVE", strategy = "native")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "NATIVE")
	@Column(name = "ID", updatable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;

	@Column(name = "TOKEN", length = 500, nullable = false, unique = true, updatable = false)
	private String token;

	@Column(name = "SIGNATURE", nullable = false, unique = true, updatable = false)
	private String signature;

	@Column(name = "ISSUEDAT", nullable = false, updatable = false)
	private Date issuedAt;

	@Column(name = "EXPIRATION", nullable = true, updatable = false)
	private Date expiration;

	/*
	 * Getters and Setters
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Date getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(Date issuedAt) {
		this.issuedAt = issuedAt;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		SessionToken other = (SessionToken) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
