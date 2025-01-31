package com.pharmacy.entity.customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Entity
@Table(name = "customers")
@SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CustomerMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_sequence", allocationSize = 1)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(nullable = false, length = 15, name = "mobile_number")
    private String mobileNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Lob
    @Column(name = "prescription_image")
    private byte[] prescriptionImage;
}