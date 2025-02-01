package com.pharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.Request.AddMedicineRequest;
import com.pharmacy.repository.MedicineRepository;
import com.pharmacy.services.MedicineService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private MedicineService medicineService;
	
	@PostMapping("/add/medicine")
	public String addMedicine(@RequestBody AddMedicineRequest request) {
		medicineService.addMedicine(request);
		return "Medicine added successfully";
		
	}
}
