package com.pharmacy.entity;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.Date;

import com.pharmacy.Request.AddMedicineRequest;
import com.pharmacy.enumm.MedicineStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String medicineName;
	private String medicineCode;
	private String batchNo;
	private String description;
	private String brandName;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Subcategory subcategory;
	private BigDecimal price;
	private Integer stockQuantity;
	private String manufacturerName;
	private Date expiryDate;

	@Lob
	private byte[] medicineImage;

	@Enumerated(EnumType.STRING)
	private MedicineStatus status;
	private Integer orderLimit;
	private BigDecimal discount;

	public Medicine(AddMedicineRequest request) {
		this.medicineName = request.getMedicineName();
		this.medicineCode = request.getMedicineCode();
		this.description = request.getDescription();
		this.brandName = request.getBrandName();
		this.price = request.getPrice();
		this.stockQuantity = request.getStockQuantity();
		this.manufacturerName = request.getManufacturerName();
		this.expiryDate = request.getExpiryDate();
		// Convert Base64 string to byte array
		if (request.getMedicineImage() != null && !request.getMedicineImage().isEmpty()) {
			System.out.println(request.getMedicineImage() + "This is ");
			this.medicineImage = Base64.getDecoder().decode(request.getMedicineImage());
			System.out.println(this.medicineImage + "This ");
		}

		this.batchNo = request.getBatchNo();
		this.status = MedicineStatus.ACTIVE;

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
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

	public MedicineStatus getStatus() {
		return status;
	}

	public void setStatus(MedicineStatus status) {
		this.status = status;
	}

	public byte[] getMedicineImage() {
		return medicineImage;
	}

	public void setMedicineImage(byte[] medicineImage) {
		this.medicineImage = medicineImage;
	}

	public Integer getOrderLimit() {
		return orderLimit;
	}

	public void setOrderLimit(Integer orderLimit) {
		this.orderLimit = orderLimit;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

}
