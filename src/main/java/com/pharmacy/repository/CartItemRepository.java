package com.pharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
