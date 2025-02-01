package com.pharmacy.entity;

import java.util.Date;

import com.pharmacy.Request.AddMedicineRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Medicine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String medicineName;
	private String medicineCode;
	private String batchNo;
	private String description;
	private String brandName;
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Subcategory subcategory;
	private double price;
	private Integer stockQuantity;
	private String manufacturerName;
	private Date expiryDate;
	private byte[] medicineImage;
	
	
	public Medicine(AddMedicineRequest request) {
		this.medicineName = request.getMedicineName();
		this.medicineCode = request.getMedicineCode();
		this.description = request.getDescription();
		this.brandName = request.getBrandName();
		this.price = request.getPrice();
		this.stockQuantity = request.getStockQuantity();
		this.manufacturerName = request.getManufacturerName();
		this.expiryDate = request.getExpiryDate();
		this.medicineImage = request.getMedicineImage();
		this.batchNo = request.getBatchNo();
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Medicine() {
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	

}
