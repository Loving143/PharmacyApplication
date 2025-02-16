package com.pharmacy.response;

import java.util.Date;

public interface LowStockMedicineResponsible {

	Integer getId();
	String getMedicineName();
	String getmedicineCode();
	String getBatchNo();
	String getDescription();
	String getBrandName();
	double getPrice();
	Integer getStockQuantity();
	Date getExpiryDate();
	byte[] getMedicineImage();
	
}
