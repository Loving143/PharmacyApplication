package com.pharmacy.Request;

import java.util.Date;

import com.pharmacy.entity.Subcategory;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

public class AddMedicineRequest {
	
	private String medicineName;
	private String medicineCode;
	private String description;
	private String brandName;
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Subcategory subcategory;
	private double price;
	private Integer stockQuantity;
	private String manufacturerName;
	private Date expiryDate;
	private byte[] medicineImage;
	
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Subcategory getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public byte[] getMedicineImage() {
		return medicineImage;
	}
	public void setMedicineImage(byte[] medicineImage) {
		this.medicineImage = medicineImage;
	}
	public String getMedicineCode() {
		return medicineCode;
	}
	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}
	
	

}
