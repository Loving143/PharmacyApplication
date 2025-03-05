package com.pharmacy.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.entity.Cart;
import com.pharmacy.entity.CartItem;
import com.pharmacy.entity.Customer;
import com.pharmacy.entity.Medicine;
import com.pharmacy.enumm.CartItemStatus;
import com.pharmacy.repository.CartItemRepository;
import com.pharmacy.repository.CartRepository;
import com.pharmacy.repository.CustomerRepository;
import com.pharmacy.repository.MedicineRepository;

@Service
public class CartService {
	@Autowired
	private MedicineRepository medicineRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	// Add item to cart
	public Cart addItemToCart(Integer customerId, Integer medicineId, Integer quantity) {
		Optional<Customer> customerOpt = customerRepository.findById(customerId);
		Optional<Medicine> medicineOpt = medicineRepository.findById(medicineId);

		if (!customerOpt.isPresent() || !medicineOpt.isPresent()) {
			throw new RuntimeException("Customer or Medicine not found");
		}

		Customer customer = customerOpt.get();
		Medicine medicine = medicineOpt.get();

		// Retrieve or create a cart for the customer
		Cart cart = cartRepository.findByCustomerId(customerId).orElseGet(() -> createNewCart(customer));

		// Check if the item already exists in the cart
		CartItem existingItem = cart.getCartItems().stream().filter(item -> item.getMedicine().equals(medicine))
				.findFirst().orElse(null);

		if (existingItem != null) {
			existingItem.setQuantity(existingItem.getQuantity() + quantity);
			existingItem.setTotalPrice(medicine.getPrice().multiply(BigDecimal.valueOf(existingItem.getQuantity())));
			existingItem.setFinalPrice(existingItem.getTotalPrice().subtract(existingItem.getDiscountAmount()));
			cartItemRepository.save(existingItem);
		} else {
			CartItem cartItem = new CartItem();
			cartItem.setCart(cart);
			cartItem.setMedicine(medicine);
			cartItem.setQuantity(quantity);
			cartItem.setUnitPrice(medicine.getPrice()); // Assume Medicine has price attribute
			cartItem.setTotalPrice(medicine.getPrice().multiply(BigDecimal.valueOf(quantity)));
//			cartItem.setDiscountAmount((medicine.getDiscount().multiply(cartItem.getTotalPrice()))
//					.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP));
			cartItem.setFinalPrice(cartItem.getTotalPrice().subtract(cartItem.getDiscountAmount()));
			cartItem.setAddedAt(LocalDateTime.now());
			cartItem.setStatus(CartItemStatus.ACTIVE); // Assume there's an enum for status
			cartItemRepository.save(cartItem);
			cart.getCartItems().add(cartItem);
			cart.setCreatedAt(LocalDateTime.now());
			cart.setUpdatedAt(LocalDateTime.now());
		
		}

		return cartRepository.save(cart);
	}

	// Create a new cart for the customer if not exists
	private Cart createNewCart(Customer customer) {
		Cart cart = new Cart();
		cart.setCustomer(customer);
		cart.setCartItems(new ArrayList<>());
		return cartRepository.save(cart);
	}

	// Get cart for a specific customer
	public Cart getCartByCustomer(Integer customerId) {
		return cartRepository.findByCustomerId(customerId)
				.orElseThrow(() -> new RuntimeException("Cart not found for customer " + customerId));
	}

	// Update item quantity
	public CartItem updateCartItemQuantity(Long cartItemId, Integer newQuantity) {

		Optional<CartItem> cartItemOpt = cartItemRepository.findById(cartItemId);
		if (!cartItemOpt.isPresent()) {
			throw new RuntimeException("CartItem not found");
		}
		CartItem cartItem = cartItemOpt.get();
		if (newQuantity == 0) {
			removeItemFromCart(cartItem.getId());
		}
		Optional<Medicine> medicineOpt = medicineRepository.findById(cartItemOpt.get().getMedicine().getId());

		if (!medicineOpt.isPresent()) {
			removeItemFromCart(cartItem.getId());
			throw new RuntimeException("Medicien Out of Stock");
		}
		Medicine medicine = medicineOpt.get();

		cartItem.setQuantity(newQuantity);
		cartItem.setTotalPrice(cartItem.getUnitPrice().multiply(BigDecimal.valueOf(newQuantity)));
		cartItem.setDiscountAmount((medicine.getDiscount().multiply(cartItem.getTotalPrice()))
				.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP));
		cartItem.setFinalPrice(cartItem.getTotalPrice().subtract(cartItem.getDiscountAmount()));
		cartItem.setUpdatedAt(LocalDateTime.now());

		return cartItemRepository.save(cartItem);
	}

	// Remove item from cart
	public void removeItemFromCart(Long cartItemId) {
		Optional<CartItem> cartItemOpt = cartItemRepository.findById(cartItemId);
		if (!cartItemOpt.isPresent()) {
			throw new RuntimeException("CartItem not found");
		}

		cartItemRepository.delete(cartItemOpt.get());
	}

	// Checkout and calculate total price
	public BigDecimal checkout(Integer customerId) {
		Cart cart = getCartByCustomer(customerId);
		BigDecimal total = cart.getCartItems().stream().map(CartItem::getFinalPrice).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		return total;
	}
}
