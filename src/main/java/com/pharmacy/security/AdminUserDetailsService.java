package com.pharmacy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pharmacy.entity.Admin;
import com.pharmacy.exception.BadRequestException;
import com.pharmacy.repository.AdminRepository;

@Service
public class AdminUserDetailsService implements UserDetailsService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminRepository.findByUserName(username).
    			orElseThrow(()->new BadRequestException("Admin with this username not found"));
		return new AdminUserDetails(admin);
		
	}

}
