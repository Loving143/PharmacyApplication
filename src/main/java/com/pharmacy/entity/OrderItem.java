package com.pharmacy.entity;

import java.math.BigDecimal;

import com.pharmacy.enumm.PrescriptionRequirement;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orderr orderr;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Medicine medicine; 

    private Integer quantity;
    
    private BigDecimal pricePerUnit;

    @Enumerated(EnumType.STRING)
    private PrescriptionRequirement prescriptionRequirement; // REQUIRED, NOT_REQUIRED

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public PrescriptionRequirement getPrescriptionRequirement() {
		return prescriptionRequirement;
	}

	public void setPrescriptionRequirement(PrescriptionRequirement prescriptionRequirement) {
		this.prescriptionRequirement = prescriptionRequirement;
	}

	public Orderr getOrderr() {
		return orderr;
	}

	public void setOrderr(Orderr orderr) {
		this.orderr = orderr;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}