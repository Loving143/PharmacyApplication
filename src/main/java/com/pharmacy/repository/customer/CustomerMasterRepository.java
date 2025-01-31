package com.pharmacy.repository.customer;

import com.pharmacy.entity.customer.CustomerMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerMasterRepository extends JpaRepository<CustomerMaster,Long> {
    Optional<CustomerMaster> findByCustomerIdOrEmailOrMobileNumber(Long customerId, String email, String mobileNumber);

    Optional<CustomerMaster> findByEmail(String email);
}
