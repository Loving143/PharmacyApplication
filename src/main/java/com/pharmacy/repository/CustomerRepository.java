package com.pharmacy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{

	Optional<Customer> findByUserName(String username);

}
