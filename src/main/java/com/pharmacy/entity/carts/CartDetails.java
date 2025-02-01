package com.pharmacy.entity.carts;

import com.pharmacy.entity.Products;
import com.pharmacy.entity.customer.CustomerMaster;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "car_details")
public class CartDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id",unique = true, nullable = false)
    private Long cartId;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private CustomerMaster customerId;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Products product_id;

    @Column(name = "is_active")
    private Boolean isActive;

}
