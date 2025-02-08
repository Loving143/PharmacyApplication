package com.pharmacy.enumm;

public enum PrescriptionRequirement {

	REQUIRED("Required"),
	NOT_REQUIRED("Not Required");
	
	private String name;

	private PrescriptionRequirement(String name) {
		this.name = name;
	}
	
}
