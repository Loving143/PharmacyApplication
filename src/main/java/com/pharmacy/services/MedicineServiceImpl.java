package com.pharmacy.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.Request.AddMedicineRequest;
import com.pharmacy.entity.Medicine;
import com.pharmacy.repository.MedicineRepository;

@Service
public class MedicineServiceImpl implements MedicineService{


	@Autowired
	private MedicineRepository medicineRepository;
	
	@Override
	public void addMedicine(AddMedicineRequest request) {
		try {
			validateAddMedicine(request);
		} catch (Exception e) {
		}
		
		Medicine medicine = new Medicine(request);
		medicineRepository.save(medicine);
	}
	public void validateAddMedicine(AddMedicineRequest request) throws Exception {
		if(request.getExpiryDate().before(new Date())) {
			throw new Exception("This medicine is already expired!");
		}
	}

}
