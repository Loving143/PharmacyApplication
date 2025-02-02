package com.pharmacy.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pharmacy.entity.Admin;

public class AdminUserDetails implements UserDetails{

	private Admin admin;
	
	public AdminUserDetails(Admin admin) {
		this.admin = admin;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return admin.getRoles().stream().map(role -> 
		new CustomGrantedAuthority(role.getName(),
				role.getDescription())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return admin.getPassword();
	}

	@Override
	public String getUsername() {
		return admin.getUserName();
	}

}
