package com.pharmacy.services;

import java.util.List;

import com.pharmacy.Request.AddMedicineRequest;
import com.pharmacy.response.ExpiredMedicineReponse;
import com.pharmacy.response.MedicineResponse;

public interface MedicineService {

	void addMedicine(AddMedicineRequest request);

	List<MedicineResponse> fetchLowStockMedicine(Integer medicineThreshhold);

	MedicineResponse fetchMedicineByMedicineCodeAndBatchNo(String medicineCode, String batchNo);

	List<ExpiredMedicineReponse> fetchExpiredMedicines();

	List<MedicineResponse> fetchAllMedicines();

}
