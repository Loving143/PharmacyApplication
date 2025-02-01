package com.pharmacy.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MedicineResponse {
	
	private String medicineName;
	private String medicineCode;
	private String batchNo;
	private String description;
	private String brandName;
	private double price;
	private Integer stockQuantity;
	private String manufacturerName;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date expiryDate;
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getMedicineCode() {
		return medicineCode;
	}
	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
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
	public MedicineResponse(LowStockMedicineResponsible dto) {
		this.batchNo = dto.getBatchNo();
		this.brandName = dto.getBrandName();
		this.medicineCode = dto.getmedicineCode();
		this.medicineName = dto.getMedicineName();
		this.description = dto.getDescription();
		this.price = dto.getPrice();
		this.stockQuantity = dto.getStockQuantity();
		this.expiryDate = dto.getExpiryDate();
	}
	
	

}
