package com.pharmacy.entity;

import java.time.LocalDate;
import java.util.Set;

import com.pharmacy.enumm.PresScriptionStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "prescription")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private Set<PrescriptionItem> prescribedItems;

    @Column(name = "doctor_name", nullable = false)
    private String doctorName;

    @Column(name = "doctor_registration_number", nullable = false)
    private String doctorRegNumber;

    @Column(name = "prescription_date", nullable = false)
    private LocalDate prescriptionDate;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;
    
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PresScriptionStatus status; 
    
    private byte[] prescriptionUploaded;

    public Prescription() {}

    public Prescription(Customer customer, String doctorName, String doctorRegNumber, LocalDate prescriptionDate, LocalDate expiryDate, String fileUrl, PresScriptionStatus status) {
        this.customer = customer;
        this.doctorName = doctorName;
        this.doctorRegNumber = doctorRegNumber;
        this.prescriptionDate = prescriptionDate;
        this.expiryDate = expiryDate;
        this.status = status;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<PrescriptionItem> getPrescribedItems() {
		return prescribedItems;
	}

	public void setPrescribedItems(Set<PrescriptionItem> prescribedItems) {
		this.prescribedItems = prescribedItems;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorRegNumber() {
		return doctorRegNumber;
	}

	public void setDoctorRegNumber(String doctorRegNumber) {
		this.doctorRegNumber = doctorRegNumber;
	}

	public LocalDate getPrescriptionDate() {
		return prescriptionDate;
	}

	public void setPrescriptionDate(LocalDate prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public PresScriptionStatus getStatus() {
		return status;
	}

	public void setStatus(PresScriptionStatus status) {
		this.status = status;
	}
    
}

