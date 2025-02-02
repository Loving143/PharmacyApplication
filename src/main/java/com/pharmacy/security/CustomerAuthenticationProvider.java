package com.pharmacy.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pharmacy.exception.BadRequestException;

public class CustomerAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired	
	private FetchUserDetailsServiceFactory userDetailsServiceFactory;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if(!(authentication instanceof CustomUsernamePasswordAuthentication)) 
			 throw new BadRequestException("Authentication token is not of expected type");
	    CustomUsernamePasswordAuthentication customAuthentication = (CustomUsernamePasswordAuthentication)authentication;
		String username = customAuthentication.getName();
        String userType = customAuthentication.getUserType();
        UserDetailsService userDetailsService = userDetailsServiceFactory.fetchUserDetailsService(userType);
        UserDetails userDetails =  userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(username, null,userDetails.getAuthorities());
    }

	@Override
	public boolean supports(Class<?> authentication) {

        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
