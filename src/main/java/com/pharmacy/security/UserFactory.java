package com.pharmacy.security;

import org.springframework.beans.factory.annotation.Autowired;

import com.pharmacy.entity.Admin;
import com.pharmacy.entity.Customer;

public class UserFactory {
	
	@Autowired
	private Customer customer;
	@Autowired
	private Admin admin;
	Object createUser(String type){
		if(type.equals("customer")) {
			return customer;
		}else if(type.equals("admin"))
			return admin;
		return null;
		
	}

}
