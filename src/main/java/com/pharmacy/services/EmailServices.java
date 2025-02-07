package com.pharmacy.services;

import java.util.Map;

public interface EmailServices {
	public void sendEmail(String recipient , String body , String subject,Map<String,Object>model);
	
	public void sendPrescriptionEmail(String to, String qrCodeBase64);
}

