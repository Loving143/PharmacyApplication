package com.pharmacy.security;

import org.springframework.security.core.GrantedAuthority;

public class CustomGrantedAuthority implements GrantedAuthority{

	private String authority;
	private String description;
	
	@Override
	public String getAuthority() {
		return authority;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setAuthority(String authority) {
		this.authority = authority;
	}


	public CustomGrantedAuthority(String authority, String description) {
		super();
		this.authority = authority;
		this.description = description;
	}

}
