package com.pharmacy.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmacy.entity.Prescription;

public class PrescriptionService {

    public static String generatePrescriptionQRCode(Prescription prescription) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(prescription);
            return QRCodeGenerator.generateQRCode(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
