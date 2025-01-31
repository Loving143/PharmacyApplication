package com.pharmacy.repository.customer;

import com.pharmacy.entity.customer.CustomerOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOtpRepository extends JpaRepository<CustomerOtp, Long> {

    void deleteAllByEmail(String email);

    List<CustomerOtp> findByEmailAndOtp(String email, Integer otp);
}
