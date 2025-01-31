package com.pharmacy.entity.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Entity
@Table(name = "customer_address")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerAddressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerMaster customerId;

    @Column(length = 500, nullable = false, name = "address")
    private String address;

    @Column(name = "is_active",nullable = false)
   // true by default
    private Boolean isActive = true;
}
