package com.pharmacy.security;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pharmacy.entity.Customer;
import com.pharmacy.entity.Role;
import com.pharmacy.exception.BadRequestException;
import com.pharmacy.repository.CustomerRepository;
import com.pharmacy.repository.RoleRepository;
@Service
public class CustomerUserDetailsService implements UserDetailsService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	      
    	Optional<Customer> customer = customerRepository.findByUserName(username);
    	if(customer.isPresent()) {
    		return new CustomUserDetails(customer.get());
    	}else {
    		Customer newCustomer = new Customer();
    		Role role =roleRepository.findByName("CUSTOMER").
    				orElseThrow(()->new BadRequestException("This role does not belong to the customer"));
    		HashSet<Role> roles = new HashSet();
			roles.add(role);
			newCustomer.setRoles(roles);
    		newCustomer.setUserName(username);
    		Customer customerr= customerRepository.save(newCustomer);
    		return new CustomUserDetails(customerr);
        	}
    	}
    
	}
