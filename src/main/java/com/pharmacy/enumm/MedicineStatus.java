package com.pharmacy.enumm;

public enum MedicineStatus {
	
	ACTIVE("Active"),
	DISPOSED("Disposed"),
	EXPIRED("Expired"),
	BANNED("Banned"),
	RETURNED("Returned");
	
	private String value;

	private MedicineStatus(String value) {
		this.value = value;
	}
}
