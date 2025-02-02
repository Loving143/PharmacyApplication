package com.pharmacy.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.entity.Otp;
import com.pharmacy.repository.OtpRepository;
@Service
public class OtpServiceImpl implements OtpService{

	@Autowired
	private OtpRepository otpRepository;
	
	@Override
	public boolean validateOtp(String username, String otpCode) {
		 Optional<Otp> otpOpt = otpRepository.findByUsernameAndOtpCode(username, otpCode);
	        if (otpOpt.isPresent() && otpOpt.get().getExpiryTime().isAfter(LocalDateTime.now())) {
	            return true;
	        }
	        return false;
	}
	
	  // Generate OTP and save it
	@Override
    public Otp generateOtp(String username) {
        String otpCode = String.format("%06d", new Random().nextInt(999999));
        Otp otp = new Otp();
        otp.setUsername(username);
        otp.setOtpCode(otpCode);
        otp.setExpiryTime(LocalDateTime.now().plusMinutes(5)); // 5 minutes expiry
        otpRepository.save(otp);
        return otp;
    }

}
