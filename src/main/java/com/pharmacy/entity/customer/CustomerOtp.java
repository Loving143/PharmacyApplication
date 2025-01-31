package com.pharmacy.entity.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_otp")
public class CustomerOtp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "otp")
    private Integer otp;

    @Column(nullable = false,name = "expiry_time")
    private Long expiryTime;

    @Column(nullable = false, name = "mobile_number")
    private String mobileNumber;

    @Column(nullable = false)
    private String email;

    @Lob
    @Column(name = "prescription_image")
    private byte[] prescriptionImage;


}
