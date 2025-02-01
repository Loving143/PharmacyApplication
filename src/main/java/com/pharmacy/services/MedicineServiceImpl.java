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
import com.pharmacy.response.LowStockMedicineResponsible;
import com.pharmacy.response.MedicineResponse;

@Service
public class MedicineServiceImpl implements MedicineService{


	@Autowired
	private MedicineRepository medicineRepository;
	
	@Autowired
	private SubCategoryRepository subcategoryRepository;
	
	@Override
	public void addMedicine(AddMedicineRequest request) {
		validateAddMedicine(request);
		Medicine medicine = null;
		Subcategory subcategory = subcategoryRepository.findById(request.getSubcategory().getId())
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
	@Override
	public List<MedicineResponse> fetchLowStockMedicine(Integer medicineThreshhold) {
		 return medicineRepository.fetchLowStockMedicine()
				 .stream().map(medicine -> new MedicineResponse(medicine))
				 .collect(Collectors.toList());

	}
	@Override
	public MedicineResponse fetchMedicineByMedicineCodeAndBatchNo(String medicineCode, String batchNo) {
		LowStockMedicineResponsible responsible =  medicineRepository.fetchMedicineByMedicineCodeAndBatchNo(medicineCode,batchNo).
					orElseThrow(()-> new BadRequestException("Medicine does not exists!"));
		MedicineResponse response = new MedicineResponse(responsible);
		return response;
	}
	@Override
	public List<MedicineResponse> fetchExpiredMedicines() {
		Date date = new Date();
		return medicineRepository.fetchExpiredMedicine(date)
				 .stream().map(medicine -> new MedicineResponse(medicine))
				 .collect(Collectors.toList());
	}

}
