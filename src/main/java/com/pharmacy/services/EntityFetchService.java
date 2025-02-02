package com.pharmacy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.exception.BadRequestException;
import com.pharmacy.repository.AdminRepository;
import com.pharmacy.repository.CustomerRepository;

@Service
public class EntityFetchService {

    @Autowired
    private FetchRepositoryStrategyFactory fetchRepositoryStrategyFactory;

    public Object getEntityByUsername(String entityType, String username) {
        if ("admin".equals(entityType)) {
            AdminRepository adminRepository = (AdminRepository) fetchRepositoryStrategyFactory.fetchRepository(entityType);
            return adminRepository.findByUserName(username).
            		orElseThrow(()->new BadRequestException("Admin with the username not found!"));
        } else if ("customer".equals(entityType)) {
            CustomerRepository customerRepository = (CustomerRepository) fetchRepositoryStrategyFactory.fetchRepository(entityType);
            return customerRepository.findByUserName(username).
            		orElseThrow(()->new BadRequestException("Customer with the username not found!"));
        }
        throw new IllegalArgumentException("Invalid entityType: " + entityType);
    }
}
