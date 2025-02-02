package com.pharmacy.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pharmacy.entity.Customer;

public class CustomUserDetails implements UserDetails{

	private static final long serialVersionUID = -2753924057463236494L;
	
	
	private Customer entity;
	
	public CustomUserDetails(Customer entity) {
		this.entity = entity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(entity instanceof Customer) {
			return entity.getRoles().stream().map(role -> 
				new CustomGrantedAuthority("ROLE_"+role.getName(),
						role.getDescription())).collect(Collectors.toList());
		}else {
			return null;
		}
		
	}

	@Override
	public String getPassword() {
		if(entity instanceof Customer)
			return ((Customer)entity).getPassword();
		return null;
	}

	@Override
	public String getUsername() {
		if(entity instanceof Customer)
			return ((Customer)entity).getUserName();
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public Customer getEntity() {
		return entity;
	}

	public void setEntity(Customer entity) {
		this.entity = entity;
	}


}
