package com.pharmacy.entity;

import java.time.LocalDate;
import java.util.Set;

import com.pharmacy.enumm.PresScriptionStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prescriptions")
public class Prescriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "Prescription_medicine_mapping",
        joinColumns = @JoinColumn(name = "prescriptionId"),  
        inverseJoinColumns = @JoinColumn(name = "medicineId") 
    )
    private Set<Medicine> medicines;

    
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
    private PresScriptionStatus status; // PENDING, APPROVED, REJECTED

    public Prescriptions() {}

    public Prescriptions(Customer customer, String doctorName, String doctorRegNumber, LocalDate prescriptionDate, LocalDate expiryDate, String fileUrl, PresScriptionStatus status) {
        this.customer = customer;
        this.doctorName = doctorName;
        this.doctorRegNumber = doctorRegNumber;
        this.prescriptionDate = prescriptionDate;
        this.expiryDate = expiryDate;
        this.status = status;
    }

    // Getters and Setters
}

