package com.pharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.entity.CustomerLoginHistory;

public interface CustomerLoginHistoryRepository extends JpaRepository<CustomerLoginHistory,Integer>{

}
