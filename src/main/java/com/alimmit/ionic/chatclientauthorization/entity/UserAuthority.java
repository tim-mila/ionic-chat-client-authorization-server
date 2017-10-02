package com.alimmit.ionic.chatclientauthorization.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
@NamedQueries({
	@NamedQuery(name = UserAuthority.DELETE_USER_AUTHORITIES, query = UserAuthority.DELETE_USER_AUTHORITIES_QUERY)
})
public class UserAuthority implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String DELETE_USER_AUTHORITIES = "deleteByUsername";
	public static final String DELETE_USER_AUTHORITIES_QUERY = "delete from UserAuthority where username=:username";
	
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	@Id
	private String username;
	private String authority;
	
	public static final UserAuthority createUser(final String username, final String role) {
		final UserAuthority authority = new UserAuthority();
		authority.setUsername(username);
		authority.setAuthority(role);
		return authority;
	}
	
	public UserAuthority() {
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(final String username) {
		this.username = username;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(final String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "UserAuthority{" + "username='" + username + '\'' + ", authority='" + authority + '\'' + '}';
	}
}
