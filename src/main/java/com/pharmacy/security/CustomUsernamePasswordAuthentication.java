package com.pharmacy.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CustomUsernamePasswordAuthentication extends UsernamePasswordAuthenticationToken{

	private String userType;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public CustomUsernamePasswordAuthentication(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities,String userType) {
		super(principal, credentials, authorities);
		this.userType = userType;
	}
	public CustomUsernamePasswordAuthentication(Object principal, Object credentials,String userType) {
		super(principal, credentials);
		this.userType = userType;
	}
	
	
}
