package com.pharmacy.services;

import com.pharmacy.entity.Otp;

public interface OtpService {

	boolean validateOtp(String username, String otpCode);
	
	Otp generateOtp(String userName);

}
