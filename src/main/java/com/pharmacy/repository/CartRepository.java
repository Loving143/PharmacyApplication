package com.pharmacy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByCustomerId(Integer customerId);
}

