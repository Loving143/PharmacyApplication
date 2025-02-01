package com.pharmacy.enumm;

public enum MedicineStatus {
	
	ACTIVE("Active"),
	DISPOSED("Disposed"),
	EXPIRED("Expired"),
	RETURNED("Returned");
	
	private String value;

	private MedicineStatus(String value) {
		this.value = value;
	}
}
