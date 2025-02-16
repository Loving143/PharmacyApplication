package com.pharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.Request.AddMedicineRequest;
import com.pharmacy.response.ResponseMessage;
import com.pharmacy.services.MedicineService;

import jakarta.transaction.Transactional;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private MedicineService medicineService;
	
	@Value("${medicine.threshhold}")
	private Integer medicineThreshhold;
	
	@Transactional
	@PostMapping("/add/medicine")
	public ResponseEntity<?> addMedicine(@RequestBody AddMedicineRequest request) {
		medicineService.addMedicine(request);
		return  ResponseEntity.ok(new ResponseMessage("1","Medicine added successfully"));
	}
	
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/get/lowStockMedicines")
	public ResponseEntity<?>fetchLowStockMedicine(){
		return ResponseEntity.ok(new ResponseMessage("1",medicineService.fetchLowStockMedicine(medicineThreshhold)));
	}
	
	@Transactional
	@GetMapping("/get/medicine/{medicineCode}/{batchNo}")
	public ResponseEntity<?>fetchMedicineByMedicineCodeAndBatchNo(@PathVariable String medicineCode,
			@PathVariable String batchNo){
		return ResponseEntity.ok(new ResponseMessage("1",medicineService.fetchMedicineByMedicineCodeAndBatchNo(medicineCode,batchNo)));
	}
	
	@GetMapping("get/expiredMedicines")
	public ResponseEntity<?>getExpiredMedicines(){
		return ResponseEntity.ok(new ResponseMessage("1", medicineService.fetchExpiredMedicines()));
	}
	
	@Transactional
	@GetMapping("get/allMedicines")
	public ResponseEntity<?>getAllMedcines(){
		return ResponseEntity.ok(new ResponseMessage("1", medicineService.fetchAllMedicines()));
	}
	
	
	
}
