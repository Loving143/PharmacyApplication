package com.pharmacy.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.entity.Cart;
import com.pharmacy.entity.CartItem;
import com.pharmacy.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	// Add an item to the cart
	@PostMapping("/{customerId}/add")
	public ResponseEntity<Cart> addItemToCart(@PathVariable Integer customerId, @RequestParam Integer medicineId,
			@RequestParam Integer quantity) {
		System.out.println("customerId"+customerId+" medicineId:"+medicineId+" quantity:"+quantity);
		Cart updatedCart = cartService.addItemToCart(customerId, medicineId, quantity);
		return ResponseEntity.ok(updatedCart);
	}

	// Get cart by customer ID
	@GetMapping("/{customerId}")
	public ResponseEntity<Cart> getCart(@PathVariable Integer customerId) {
		Cart cart = cartService.getCartByCustomer(customerId);
		return ResponseEntity.ok(cart);
	}

	// Update item quantity
	@PutMapping("/item/{cartItemId}/update")
	public ResponseEntity<CartItem> updateCartItemQuantity(@PathVariable Long cartItemId,
			@RequestParam Integer quantity) {
		CartItem updatedItem = cartService.updateCartItemQuantity(cartItemId, quantity);
		return ResponseEntity.ok(updatedItem);
	}

	// Remove an item from the cart
	@DeleteMapping("/item/{cartItemId}/remove")
	public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartItemId) {
		cartService.removeItemFromCart(cartItemId);
		return ResponseEntity.noContent().build();
	}

	// Checkout the cart and get total price
	@GetMapping("/{customerId}/checkout")
	public ResponseEntity<BigDecimal> checkout(@PathVariable Integer customerId) {
		BigDecimal totalAmount = cartService.checkout(customerId);
		return ResponseEntity.ok(totalAmount);
	}

}
