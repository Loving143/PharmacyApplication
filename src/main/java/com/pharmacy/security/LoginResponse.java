package com.pharmacy.security;

import java.util.stream.Collectors;

import com.pharmacy.entity.Customer;
import com.pharmacy.response.UserResponse;

public class LoginResponse {
	
	private String token;
	private UserResponse user;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserResponse getUser() {
		return user;
	}
	public void setUser(UserResponse user) {
		this.user = user;
	}
	
	public LoginResponse(String token , Customer user) {
		this.token = token;
		UserResponse userResponse = new UserResponse();
		userResponse.setRole(user.getRoles().stream().
				map(role->role.getName()).collect(Collectors.toList()));
		userResponse.setUserName(user.getUserName());
		userResponse.setEmail(user.getEmail());
		userResponse.setId(user.getId());
		this.user = userResponse;
	}

	
}
