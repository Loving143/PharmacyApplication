package com.pharmacy.enumm;

public enum CartItemStatus {
    ACTIVE("Active"),  
    REMOVED("Removed"),   
    PURCHASED("Purchased");
	
	private String name;

	private CartItemStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
