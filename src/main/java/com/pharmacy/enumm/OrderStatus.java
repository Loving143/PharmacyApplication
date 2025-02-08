package com.pharmacy.enumm;

public enum OrderStatus {

	PENDING("Pending"),
	SHIPPED("Shipped"),
	DELIVERED("Delivered"),
	CANCELLED("Cancelled");
	
	private String name;

	private OrderStatus(String name) {
		this.name = name;
	}
	
	
}
//PENDING, SHIPPED, DELIVERED, CANCELED
