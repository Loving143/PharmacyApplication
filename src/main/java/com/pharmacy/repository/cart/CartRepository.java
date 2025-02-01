package com.pharmacy.repository.cart;

import com.pharmacy.entity.carts.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartDetails,Long> {

}
