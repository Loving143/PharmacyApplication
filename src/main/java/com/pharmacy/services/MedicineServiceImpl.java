package com.pharmacy.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.Request.AddMedicineRequest;
import com.pharmacy.entity.Medicine;
import com.pharmacy.entity.Subcategory;
import com.pharmacy.exception.BadRequestException;
import com.pharmacy.repository.MedicineRepository;
import com.pharmacy.repository.SubCategoryRepository;
import com.pharmacy.response.ExpiredMedicineReponse;
import com.pharmacy.response.LowStockMedicineResponsible;
import com.pharmacy.response.MedicineResponse;

import jakarta.transaction.Transactional;

@Service
public class MedicineServiceImpl implements MedicineService{


	@Autowired
	private MedicineRepository medicineRepository;
	
	@Autowired
	private SubCategoryRepository subcategoryRepository;
	
	@Transactional
	@Override
	public void addMedicine(AddMedicineRequest request) {
		validateAddMedicine(request);
		Medicine medicine = null;
		Subcategory subcategory = subcategoryRepository.findById(request.getSubcategoryId())
		.orElseThrow(()-> new BadRequestException("Subcategory not found!"));
		if(medicineRepository.existsByMedicineCodeAndBatchNo(request.getMedicineCode(),request.getBatchNo())) {
			medicine =	medicineRepository.fetchMedicineByMedicneCodeAndBatchNo(request.getMedicineCode(),request.getBatchNo());
			int stockQuantity = medicine.getStockQuantity()+request.getStockQuantity();
			medicine.setStockQuantity(stockQuantity);
		}else {
			if(medicineRepository.existsByMedicineCodeAndExpiryDate(request.getMedicineCode(),request.getExpiryDate()))
					throw new BadRequestException("Same medicine having different batches can not have same expiry date!");
				medicine = new Medicine(request);
				medicine.setSubcategory(subcategory);
		}
		medicineRepository.save(medicine);
	}
	public void validateAddMedicine(AddMedicineRequest request)  {
	}
	
	@Transactional
	@Override
	public List<MedicineResponse> fetchLowStockMedicine(Integer medicineThreshhold) {
		 return medicineRepository.fetchLowStockMedicine(medicineThreshhold)
				 .stream().map(medicine -> new MedicineResponse(medicine))
				 .collect(Collectors.toList());
	
	}
	@Transactional
	@Override
	public MedicineResponse fetchMedicineByMedicineCodeAndBatchNo(String medicineCode, String batchNo) {
		LowStockMedicineResponsible responsible =  medicineRepository.fetchMedicineByMedicineCodeAndBatchNo(medicineCode,batchNo).
					orElseThrow(()-> new BadRequestException("Medicine does not exists!"));
		MedicineResponse response = new MedicineResponse(responsible);
		return response;
	}
	@Override
	public List<ExpiredMedicineReponse> fetchExpiredMedicines() {
		Date date = new Date();
		return medicineRepository.fetchExpiredMedicine(date)
				 .stream().map((medicine) -> 
					 new ExpiredMedicineReponse(medicine)
				 )
				 .collect(Collectors.toList());
		
	}
	
	@Transactional
	@Override
	public List<MedicineResponse> fetchAllMedicines() {
		return medicineRepository.fetchAllMedicine()
				 .stream().map(medicine -> new MedicineResponse(medicine))
				 .collect(Collectors.toList());
		
	}

}
