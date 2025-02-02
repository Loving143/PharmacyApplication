package com.pharmacy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pharmacy.entity.Otp;

public interface OtpRepository extends JpaRepository<Otp,Integer>{

	Optional<Otp> findByUsernameAndOtpCode(String username, String otpCode);

}
